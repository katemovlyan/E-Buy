package ua.com.codefire.ecommerce.web.controller.web_controllers;

import com.liqpay.LiqPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.com.codefire.ecommerce.data.entity.Product;
import ua.com.codefire.ecommerce.data.entity.ShoppingCart;
import ua.com.codefire.ecommerce.data.entity.ShoppingCartItem;
import ua.com.codefire.ecommerce.data.entity.User;
import ua.com.codefire.ecommerce.data.service.ProductService;
import ua.com.codefire.ecommerce.utils.AttributeNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valery on 15.02.2017.
 */
@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    private ShoppingCart getCartContent() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        return getCartContent(attr.getRequest());
    }

    private ShoppingCart getCartContent(HttpServletRequest request) {
        HttpSession session = request.getSession();

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart);
        }

        return shoppingCart;
    }


    @RequestMapping("/cart")
    public String getCart(Model model, HttpSession session) {
        ShoppingCart cart = getCartContent();
        model.addAttribute("shoppingCart", cart);

        model.addAttribute("payBtn", getPayBtn(cart, (User) session.getAttribute(AttributeNames.SESSION_USER)));

        return "/products/cart";
    }

    @RequestMapping("/cart/add")
    public String addToCart(@RequestParam int productId, HttpServletRequest request, Model model) {

        getCartContent(request).addProductToCart(productService.getProduct(productId));

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/cart/remove")
    public String removeFromCart(@RequestParam int productId, Model model) {
        getCartContent().removeProductFromCart(productService.getProduct(productId));

        return "redirect:/cart";
    }

    private String getPayBtn(ShoppingCart cart, User currUser) {
        StringBuilder nameSb = new StringBuilder();
        double commonPrice = 0;
        ShoppingCartItem[] productsToBuy = cart.getItems().values().toArray(new ShoppingCartItem[cart.getItems().size()]);
        for (int i = 0; i < productsToBuy.length; i++) {
            Product productToBuy = productsToBuy[i].getProduct();
            commonPrice += productsToBuy[i].getSum();
            nameSb.append(productsToBuy[i].getAmount()).append("*").append(productToBuy.getModel());
            if (i < productsToBuy.length - 1)
                nameSb.append(", ");
        }

        Map params = new HashMap();
        params.put("action", "pay");
        params.put("sender_first_name", currUser.getUsername());
        params.put("product_name", nameSb.toString());
        params.put("amount", commonPrice);
        params.put("description", nameSb.toString());
        params.put("currency", "USD");
        params.put("language", "en");
        params.put("sandbox", "1"); // enable the testing environment and card will NOT charged. If not set will be used property isCnbSandbox()
        LiqPay liqpay = new LiqPay("i35824563871", "fumlSxNaVnyXRDLhSS3CC6Ui2l7LzRNACxouqgYK");
        String html = liqpay.cnb_form(params);

        return html;
//        return "redirect:/cart";
    }

}
