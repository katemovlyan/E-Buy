package ua.com.codefire.ecommerce.web.controller.web_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.codefire.ecommerce.data.entity.Product;
import ua.com.codefire.ecommerce.data.entity.ProductType;
import ua.com.codefire.ecommerce.data.service.ProductService;

import java.util.List;

/**
 * Created by Valery on 02.02.2017.
 */
@Controller
public class FilterController {

    @Autowired
    private ProductService service;

    @RequestMapping("/filter")
    public String getFilter(Model model) {

        List<ProductType> productTypes = service.getAllProductTypes();
        List<Product> products = service.getAllProducts();

        model.addAttribute("product_types", productTypes);
        model.addAttribute("products", products);
        model.addAttribute("totalProductsCount", products.size());
        model.addAttribute("filteredProductsCount", products.size());

        return "products/filter";
    }

    @RequestMapping(value = "/filter/{id}")
    public String getFilter(@PathVariable("id") String id, Model model) {

        List<ProductType> productTypes = service.getAllProductTypes();
        List<Product> products = null;
        if (id == null || id.isEmpty()) {
            products = service.getAllProducts();
        } else {
            products = service.getProductsByTypeId(Integer.parseInt(id));
        }

        model.addAttribute("product_types", productTypes);
        model.addAttribute("products", products);
        model.addAttribute("totalProductsCount", service.getProductsAmount());
        model.addAttribute("filteredProductsCount", products.size());

        return "products/filter";
    }
}

