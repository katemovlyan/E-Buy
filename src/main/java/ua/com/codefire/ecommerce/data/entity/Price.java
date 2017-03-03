package ua.com.codefire.ecommerce.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Katya on 02.02.2017.
 */
@Entity
@Table(name = "product_prices")
@NamedQueries({
        @NamedQuery(name = "Price.findAll", query = "SELECT p FROM Price p"),
        @NamedQuery(name = "Price.findValueById", query= "SELECT p.value FROM Price p WHERE p.id = :priceId"),
        @NamedQuery(name = "Price.findTopical", query = "SELECT p FROM Price p WHERE p.isTopical = true AND p.product.id = :productId")
})
public class Price implements Serializable {
    @Id
    @Column(name = "product_price_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="price_value")
    private Double value;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    @Column(name = "is_topical")
    private boolean isTopical;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "price_currency_id")
    private Currency currency;

    public Price() {
    }

    public Price(Double value, Timestamp lastUpdated, Currency currency) {
        this.value = value;
        this.lastUpdated = lastUpdated;
        this.currency = currency;
    }

    public Price(Double value, Timestamp lastUpdated, boolean isTopical, Product product, Currency currency) {
        this.value = value;
        this.lastUpdated = lastUpdated;
        this.isTopical = isTopical;
        this.product = product;
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public ua.com.codefire.ecommerce.data.entity.Currency getCurrency() {
        return currency;
    }

    public void setCurrency(ua.com.codefire.ecommerce.data.entity.Currency currency) {
        this.currency = currency;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean getIsTopical() {
        return isTopical;
    }

    public void setIsTopical(boolean topical) {
        isTopical = topical;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (isTopical != price.isTopical) return false;
        if (getId() != null ? !getId().equals(price.getId()) : price.getId() != null) return false;
        if (!getValue().equals(price.getValue())) return false;
        if (!getLastUpdated().equals(price.getLastUpdated())) return false;
        if (!getProduct().equals(price.getProduct())) return false;
        return getCurrency().equals(price.getCurrency());

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getValue().hashCode();
        result = 31 * result + getLastUpdated().hashCode();
        result = 31 * result + (isTopical ? 1 : 0);
        result = 31 * result + getProduct().hashCode();
        result = 31 * result + getCurrency().hashCode();
        return result;
    }
}
