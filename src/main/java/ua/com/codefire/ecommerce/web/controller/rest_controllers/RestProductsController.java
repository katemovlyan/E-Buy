package ua.com.codefire.ecommerce.web.controller.rest_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ua.com.codefire.ecommerce.data.entity.*;
import ua.com.codefire.ecommerce.data.service.PriceService;
import ua.com.codefire.ecommerce.data.service.ProductService;
import ua.com.codefire.ecommerce.data.service.UserService;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by ankys on 15.02.2017.
 */
@RequestMapping("/rest/products")
@RestController
public class RestProductsController {
    @Autowired
    private ProductService productService;
    @Autowired
    private PriceService priceService;

    private static final int amountByPage = 20;

    @RequestMapping({"/", "/index"})
    public List<Product> getProductsRest(Model model) {
        return productService.getProductsByPage(1, amountByPage);
    }

    @RequestMapping("/amount")
    public ResponseEntity<Long> getAmountOfProductsRest() {
        return new ResponseEntity<Long>(productService.getProductsAmount(), HttpStatus.OK);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Boolean> postCreateProductRest(@Validated @ModelAttribute Price price,
                                                     @RequestParam CommonsMultipartFile[] fileUpload, BindingResult result) {
        price.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        price.setIsTopical(true);

        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload) {
                if (aFile.getSize() > 0) {
                    price.getProduct().setPhoto(aFile.getBytes());

                }
            }
        }
        try {
            if (!result.hasErrors()) {
                productService.createProduct(price.getProduct());
                priceService.createPrice(price);
            }
            ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);
            response.getHeaders().add("message", "Successfully created");
            return response;
        } catch (Exception ex) {
            ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.INTERNAL_SERVER_ERROR);
            response.getHeaders().add("message", "Problems, while creating product. Exception: \n" + ex);
            return response;
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductRest(@RequestParam int productId) {
        return new ResponseEntity<Product>(productService.getProduct(productId), HttpStatus.OK);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<Boolean> postUpdateProductRest(@ModelAttribute Price price,
                                                     @RequestParam CommonsMultipartFile[] fileUpload) {
        try {
            Price topicalPrice = priceService.getTopicalPrice(price.getProduct().getId());
            byte[] photo = topicalPrice.getProduct().getPhoto();

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

            ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);
            response.getHeaders().add("message", "Successfully added");
            return response;
        } catch (Exception e) {
            ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.INTERNAL_SERVER_ERROR);
            response.getHeaders().add("message", "Problems, while adding to db. Exception: \n" + e);
            return response;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> postDeleteProductRest(@RequestParam int productId) {
        try {
            productService.deleteProduct(productService.getProduct(productId));

            ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);
            response.getHeaders().add("message", "Successfully deleted");
            return response;
        } catch (Exception ex) {
            ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(false, HttpStatus.OK);
            response.getHeaders().add("message", "Problems, while deleting");
            return response;
        }
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String postBuyProductRest(Product productToBuy) {
        return "redirect:/";
    }
}
