package ua.com.codefire.ecommerce.web.controller.rest_controllers;

import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.com.codefire.ecommerce.data.entity.ShoppingCart;
import ua.com.codefire.ecommerce.data.entity.ShoppingCartItem;
import ua.com.codefire.ecommerce.data.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Valery on 16.02.2017.
 */
@RequestMapping("/rest/cart")
@RestController
public class RestCartController {
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

    @RequestMapping({"", "/", "/index"})
    public String getCart() {
        JsonArray jsonArray = new JsonArray();
        for (ShoppingCartItem shoppingCartItem:getCartContent().getItems().values()
                ) {
            JsonObject jShoppingCartItem = new JsonObject();
            jShoppingCartItem.put("productId", shoppingCartItem.getProduct().getId());
            jShoppingCartItem.put("amount", shoppingCartItem.getAmount());
            jShoppingCartItem.put("price", shoppingCartItem.getPrice());
            jShoppingCartItem.put("sum", shoppingCartItem.getSum());

            jsonArray.add(jShoppingCartItem);
        }

        return jsonArray.toJson();
    }

    @RequestMapping("/total")
    public String getTotal() {
        return Double.toString(getCartContent().getTotal());
    }

    @RequestMapping("/add")
    public void addProduct(@RequestParam int id) {
        getCartContent().addProductToCart(productService.getProduct(id));
    }

}
