package ua.com.codefire.ecommerce.web.controller.web_controllers.client;

import com.liqpay.LiqPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ua.com.codefire.ecommerce.data.entity.*;
import ua.com.codefire.ecommerce.data.service.PriceService;
import ua.com.codefire.ecommerce.data.service.ProductService;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Katya on 16.02.2017.
 */
//@RequestMapping("/client/products")
@Controller
public class ClientProductsController {
    @Autowired
    ProductService productService;

    @Autowired
    PriceService priceService;

    private static final int amountByPage = 20;

    @RequestMapping(value = {"/", "/client/products"})
    public String getProducts(Model model) {
        List<Product> productsByPage = productService.getProductsByPage(1, amountByPage);
        long totalProducts = productService.getProductsAmount();
        long remainder = totalProducts % amountByPage;

        model.addAttribute("products", productsByPage);
        model.addAttribute("totalProductsCount", totalProducts);
        model.addAttribute("numberOfPages", (int) (Math.ceil(totalProducts / amountByPage) + remainder / 10));

        return "products/productsTable";
    }
    @RequestMapping(value = {"/list", "/client/products/list"}, method = RequestMethod.GET)
    public String getProducts(@RequestParam Integer pageNumber, @RequestParam Integer amountPerPage, Model model) {
        List<Product> productsByPage = productService.getProductsByPage(pageNumber, amountPerPage);
        Map<Product, String> productPhoto = new HashMap<>();

        for (Product product: productsByPage) {
            if (product.getPhoto() != null) {
                byte[] photo64 = Base64.getEncoder().encode(product.getPhoto());
                productPhoto.put(product, new String(photo64));
            } else productPhoto.put(product, "");
        }

        long totalProducts = productService.getProductsAmount();
        long remainder = totalProducts % amountByPage;

        model.addAttribute("numberOfPages", (int) (Math.ceil(totalProducts / amountByPage) + remainder / 10));
        model.addAttribute("photo", productPhoto);
        model.addAttribute("products", productsByPage);
        model.addAttribute("totalProductsCount", productService.getProductsAmount());

        return "products/productsTable";
    }

    @RequestMapping(value = "/client/products/buy", method = RequestMethod.POST)
    public String postDeleteProduct(@ModelAttribute Product product) {
        Map params = new HashMap();
        params.put("sandbox", "1"); // enable the testing environment and card will NOT charged. If not set will be used property isCnbSandbox()
        LiqPay liqpay = new LiqPay("i35824563871", "fumlSxNaVnyXRDLhSS3CC6Ui2l7LzRNACxouqgYK");
        String html = liqpay.cnb_form(params);
        return html;
    }

}
