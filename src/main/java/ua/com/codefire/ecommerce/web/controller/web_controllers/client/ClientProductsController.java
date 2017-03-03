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
 * Created by ankys on 16.02.2017.
 */
@RequestMapping("/client/products")
@Controller
public class ClientProductsController {
    @Autowired
    ProductService productService;

    @Autowired
    PriceService priceService;

    private static final int amountByPage = 20;

    @RequestMapping("/")
    public String getProducts(Model model) {
        List<Product> productsByPage = productService.getProductsByPage(1, amountByPage);
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

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateProductPage(Model model) {

        Price topicalPrice = new Price();
        topicalPrice.setCurrency(new Currency());
        topicalPrice.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        topicalPrice.setProduct(new Product());
        topicalPrice.getProduct().setProductType(new ProductType());
        topicalPrice.getProduct().setBrand(new Brand());

        model.addAttribute("currencies", priceService.getAllCurrencies());
        model.addAttribute("types", productService.getAllProductTypes());
        model.addAttribute("brands", productService.getAllBrands());
        model.addAttribute("topicalPrice", topicalPrice);

        return "products/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postCreateProduct(@Validated @ModelAttribute Price price,
                                    @RequestParam CommonsMultipartFile[] fileUpload, BindingResult result) {

        price.getCurrency().setName(priceService.getCurrencyNameById(price.getCurrency().getId()));
        price.getProduct().getBrand().setName(productService.getBrandNameById(price.getProduct().getBrand().getId()));
        price.getProduct().getProductType().setName(productService.getProductTypeNameById(price.getProduct().getProductType().getId()));

        price.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        price.setIsTopical(true);

        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload) {
                if (aFile.getSize() > 0) {
                    price.getProduct().setPhoto(aFile.getBytes());
                }
            }
        }

        if (!result.hasErrors()) {
            productService.createProduct(price.getProduct());
            priceService.createPrice(price);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getProductEditPage(@RequestParam int productId, Model model) {

        Price productToEditTopicalPrice = priceService.getTopicalPrice(productId);

        if (productToEditTopicalPrice.getProduct().getPhoto() != null) {
            byte[] photo64 = Base64.getEncoder().encode(productToEditTopicalPrice.getProduct().getPhoto());
            model.addAttribute("productImage", new String(photo64));
        }

        model.addAttribute("topicalPrice", productToEditTopicalPrice);
        model.addAttribute("currencies", priceService.getAllCurrencies());
        model.addAttribute("types", productService.getAllProductTypes());
        model.addAttribute("brands", productService.getAllBrands());
        model.addAttribute("productToEditPriceValue", productToEditTopicalPrice.getValue());

        return "products/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postUpdateProduct(@ModelAttribute Price price, @RequestParam CommonsMultipartFile[] fileUpload) {

        Price topicalPrice = priceService.getTopicalPrice(price.getProduct().getId());
        byte[] photo = topicalPrice.getProduct().getPhoto();

        price.getCurrency().setName(priceService.getCurrencyNameById(price.getCurrency().getId()));
        price.getProduct().getBrand().setName(productService.getBrandNameById(price.getProduct().getBrand().getId()));
        price.getProduct().getProductType().setName(productService.getProductTypeNameById(price.getProduct().getProductType().getId()));

        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload) {
                if (aFile.getSize() > 0) {
                    price.getProduct().setPhoto(aFile.getBytes());
                }
            }
            if (fileUpload[0].getSize() == 0) {
                price.getProduct().setPhoto(photo);
            }
        }

        if (topicalPrice.getValue() == price.getValue() && topicalPrice.getCurrency().getName().equals(price.getCurrency().getName())) {
            topicalPrice.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        } else {
            price.setId(null);
            topicalPrice.setIsTopical(false);
            priceService.createPrice(price);
        }

        productService.updateProduct(price.getProduct());
        priceService.updatePrice(topicalPrice);

        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postDeleteProduct(@RequestParam int productId) {

        productService.deleteProduct(productService.getProduct(productId));

        return "redirect:/";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String postDeleteProduct(@ModelAttribute Product product) {
        Map params = new HashMap();
        params.put("sandbox", "1"); // enable the testing environment and card will NOT charged. If not set will be used property isCnbSandbox()
        LiqPay liqpay = new LiqPay("i35824563871", "fumlSxNaVnyXRDLhSS3CC6Ui2l7LzRNACxouqgYK");
        String html = liqpay.cnb_form(params);
        return html;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String index(@RequestParam Integer pageNumber, @RequestParam Integer amountPerPage, Model model) {
        List<Product> allProducts = productService.getProductsByPage(pageNumber, amountPerPage);
        model.addAttribute("products", allProducts);
        model.addAttribute("totalProductsCount", productService.getProductsAmount());

        long totalProducts = productService.getProductsAmount();
        long remainder = totalProducts % amountByPage;
        model.addAttribute("numberOfPages", (int) (Math.ceil(totalProducts / amountByPage) + remainder / 10));

        return "products/list";
    }
}
