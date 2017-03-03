package ua.com.codefire.ecommerce.web.controller.web_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.codefire.ecommerce.data.entity.Brand;
import ua.com.codefire.ecommerce.data.entity.Currency;
import ua.com.codefire.ecommerce.data.entity.ProductType;
import ua.com.codefire.ecommerce.data.service.PriceService;
import ua.com.codefire.ecommerce.data.service.ProductService;

/**
 * Created by Katya on 16.02.2017.
 */
@RequestMapping("/productDependencies")
@Controller
public class ProductDependenciesController {
    @Autowired
    ProductService productService;
    @Autowired
    PriceService priceService;

    @RequestMapping("/productType/add")
    public void addProductType(@RequestParam String productTypeName) {
        productService.createProductType(new ProductType(productTypeName));
    }

    @RequestMapping("/productBrand/add")
    public void addProductBrand(@RequestParam String productBrandName) {
        productService.createBrand(new Brand(productBrandName));
    }

    @RequestMapping("/productCurrency/add")
    public void addProductCurrency(@RequestParam String productCurrencyName) {
        priceService.createCurrency(new Currency(productCurrencyName));
    }
}
