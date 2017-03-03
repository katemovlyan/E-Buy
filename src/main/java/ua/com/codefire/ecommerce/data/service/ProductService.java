package ua.com.codefire.ecommerce.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.codefire.ecommerce.data.entity.Brand;
import ua.com.codefire.ecommerce.data.entity.Currency;
import ua.com.codefire.ecommerce.data.entity.Product;
import ua.com.codefire.ecommerce.data.entity.ProductType;
import ua.com.codefire.ecommerce.data.repo.BrandRepo;
import ua.com.codefire.ecommerce.data.repo.CurrencyRepo;
import ua.com.codefire.ecommerce.data.repo.ProductRepo;
import ua.com.codefire.ecommerce.data.repo.ProductTypeRepo;

import java.util.List;

/**
 * Created by Katya on 02.02.17.
 */
@Service
public class ProductService{

    @Autowired
    private BrandRepo brandRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductTypeRepo productTypeRepo;
    @Autowired
    private CurrencyRepo currencyRepo;

    public List<Brand> getAllBrands() {
        return brandRepo.findAll();
    }
    public List<ProductType> getAllProductTypes() {
        return productTypeRepo.findAll();
    }
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    public List<Currency> getAllCurrencies() {return currencyRepo.findAll(); }

    public void createBrand(Brand brand){
        brandRepo.add(brand);
    }
    public void createProductType(ProductType type){
        productTypeRepo.add(type);
    }
    public void createProduct(Product product){
        productRepo.add(product);
    }

    public Brand getBrand(int id){
        return brandRepo.getById(id);
    }
    public ProductType getProductType(int id){
        return productTypeRepo.getById(id);
    }
    public Product getProduct(int id){
        return productRepo.getById(id);
    }

    public void updateBrand(Brand brand){
        brandRepo.update(brand);
    }
    public void updateProductType(ProductType type){
        productTypeRepo.update(type);
    }
    public void updateProduct(Product product){
        productRepo.update(product);
    }

    public void deleteBrand(Brand brand){
        brandRepo.delete(brand);
    }
    public void deleteProductType(ProductType type){
        productTypeRepo.delete(type);
    }
    public void deleteProduct(Product product){
        productRepo.delete(product);
    }

    public String getBrandNameById(int id) {
        return brandRepo.getNameById(id);
    }
    public String getProductTypeNameById(int id) {
        return productTypeRepo.getNameById(id);
    }
    public List<Product> getProductsByPage(int pageNumber, int amountByPage) {
        return productRepo.getProductsByPage(pageNumber, amountByPage);
    }

    public long getProductsAmount() {
        return productRepo.getProductsAmount();
    }

    public List<Product> getProductsByTypeId(int id) { return productTypeRepo.findProductsByTypeId(id); }

}
