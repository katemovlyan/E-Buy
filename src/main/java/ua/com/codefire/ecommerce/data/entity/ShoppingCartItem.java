package ua.com.codefire.ecommerce.data.entity;

import java.io.Serializable;

/**
 * Created by Valery on 15.02.2017.
 */
public class ShoppingCartItem implements Serializable {
    private Product product;
    private int amount;

    public ShoppingCartItem(Product product) {
        this.product = product;
        this.amount = 1;
    }

    public ShoppingCartItem(Product product, int amount) {
        this.product = product;
        this.amount = amount ;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        for (Price price:product.getPrices()
             ) {
            if (price.getIsTopical()) {
                return price.getValue();
            }
        }

        return 0;
    }

    public int increaseAmount() {
        amount = Integer.min(amount+1, Integer.MAX_VALUE);
        return amount;
    }

    public int decreaseAmount() {
        amount = Integer.max(amount-1,0);
        return amount;
    }

    public double getSum() {
        return amount*getPrice();
    }

}
