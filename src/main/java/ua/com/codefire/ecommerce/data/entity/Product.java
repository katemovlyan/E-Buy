package ua.com.codefire.ecommerce.data.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Katya on 31.01.2017.
 */
@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.getCount", query = "SELECT COUNT(p.id) FROM Product p")
})
public class Product implements Serializable {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "product")
    @Cascade(CascadeType.ALL)
    private List<Price> prices;

    @Lob
    @Column
    private String description;

    @Lob
    @Column(length = 20971520)
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "product_brand_id")
    private Brand brand;

    private String model;

    private int sold;

    private int availableAmount;

    public Price findTopicalPrice() {
        Price topicalPrice = null;
        if (prices != null) {
            for (Price p : prices) {
                if (p.getIsTopical()) {
                    topicalPrice = p;
                    break;
                }
            }
        }
        return topicalPrice;
    }

    public Product() {

    }

    public Product(ProductType productType, Brand brand, String model) {
        this.productType = productType;
        this.brand = brand;
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (getSold() != product.getSold()) return false;
        if (getAvailableAmount() != product.getAvailableAmount()) return false;
        if (getId() != null ? !getId().equals(product.getId()) : product.getId() != null) return false;
        if (getPrices() != null ? !getPrices().equals(product.getPrices()) : product.getPrices() != null) return false;
        if (getDescription() != null ? !getDescription().equals(product.getDescription()) : product.getDescription() != null)
            return false;
        if (!Arrays.equals(getPhoto(), product.getPhoto())) return false;
        if (getProductType() != null ? !getProductType().equals(product.getProductType()) : product.getProductType() != null)
            return false;
        if (getBrand() != null ? !getBrand().equals(product.getBrand()) : product.getBrand() != null) return false;
        return getModel() != null ? getModel().equals(product.getModel()) : product.getModel() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPrices() != null ? getPrices().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getPhoto());
        result = 31 * result + (getProductType() != null ? getProductType().hashCode() : 0);
        result = 31 * result + (getBrand() != null ? getBrand().hashCode() : 0);
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 31 * result + getSold();
        result = 31 * result + getAvailableAmount();
        return result;
    }
}
