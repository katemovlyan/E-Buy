package ua.com.codefire.ecommerce.web.controller.web_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ua.com.codefire.ecommerce.data.entity.*;
import ua.com.codefire.ecommerce.data.entity.Currency;
import ua.com.codefire.ecommerce.data.service.PriceService;
import ua.com.codefire.ecommerce.data.service.ProductService;
import ua.com.codefire.ecommerce.data.service.UserService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by Katya on 31.01.17.
 */
@RequestMapping("/")
@Controller
public class IndexController {
    @Autowired
    private ProductService productService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private UserService userService;

    private static final List<String> productModels = new ArrayList<>(Arrays.asList("E330", "Xperia C1505", "Xperia Z5",
            "XperiaZ3", "Vaio", "IPhone 5S", "IPhone 7", "MacBook Air"));

    private static final int amountByPage = 20;

    @RequestMapping("/index")
    public String getIndex(Model model) {
        List<Product> productsByPage = productService.getProductsByPage(1, amountByPage);
        long totalProducts = productService.getProductsAmount();
        long remainder = totalProducts % amountByPage;

        model.addAttribute("products", productsByPage);
        model.addAttribute("totalProductsCount", totalProducts);
        model.addAttribute("numberOfPages", (int) (Math.ceil(totalProducts / amountByPage) + remainder / 10));

        return "products/productsTable";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister() {
        return "common/registration";
    }

//    @RequestMapping(value = "/init", method = RequestMethod.GET)
    @PostConstruct
    public void initValues() {
        if (productService.getProductsAmount() == 0) {

            initUsers();
            initBrands("Apple", "Sony", "Lenovo", "Xiaomi", "Asus");
            initCurrencies("$", "€");
            initProductTypes("Mobile", "Notebook", "Furniture");
            initProducts();
            initPrices();
        }
//        return "redirect:/";
    }

    private void initBrands(String... brands) {
        for (String brand : brands) {
            productService.createBrand(new Brand(brand));
        }
    }

    private void initCurrencies(String... currencies) {
        for (String currency : currencies) {
            priceService.createCurrency(new Currency(currency));
        }
    }

    private void initProductTypes(String... productTypes) {
        for (String productType : productTypes) {
            productService.createProductType(new ProductType(productType));
        }
    }

    private void initProducts() {
        List<Brand> brands = productService.getAllBrands();
        List<ProductType> productTypes = productService.getAllProductTypes();
        for (int i = 0; i < 5; i++) {
            productTypes.forEach(productType -> {
                brands.forEach(brand -> {
                    productService.createProduct(new Product(productType,
                            brand, productModels.get((new Random()).nextInt(productModels.size()))));
                });
            });
        }
    }

    private void initPrices() {
        List<Product> products = productService.getAllProducts();
        List<Currency> currencies = priceService.getAllCurrencies();
        List<Integer> currencyIds = currencies.stream()
                .map(Currency::getId)
                .collect(Collectors.toList());

        products.forEach(product -> {
            currencies.forEach(currency -> {
                if (currency.getName().equals(currencies.get(0).getName())) {
                    priceService.createPrice(
                            new Price((double) ThreadLocalRandom.current().nextInt(100, 1000),
                                    Timestamp.valueOf(LocalDateTime.now()),
                                    true,
                                    product,
                                    currency));
                } else {
                    priceService.createPrice(
                            new Price((double) ThreadLocalRandom.current().nextInt(100, 1000),
                                    Timestamp.valueOf(LocalDateTime.now()),
                                    false,
                                    product,
                                    currency));
                }
            });
        });
    }

    private void initUsers() {
        User newUser = new User("test", "12345", "test@gmail.com", User.AccessLevel.User);
        userService.create(newUser);
    }
}
