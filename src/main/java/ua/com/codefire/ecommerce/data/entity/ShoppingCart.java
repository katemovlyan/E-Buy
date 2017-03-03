package ua.com.codefire.ecommerce.data.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Valery on 15.02.2017.
 */
public class ShoppingCart implements Serializable {
    private Map<Product, ShoppingCartItem> items;

    public ShoppingCart() {
        items = new HashMap();
    }

    public Map<Product, ShoppingCartItem> getItems() {
        return items;
    }

    public void addProductToCart(Product product) {
        increaseProductAmount(product);
    }

    public void removeProductFromCart(Product product) {
        if (items.get(product) != null) {
            items.remove(product);
        }
    }

    public void emptyCart() {
        if (items.size() != 0) {
            items.clear();
        }
    }

    public void changeProductAmount(Product product, int newAmount) {
        items.get(product).setAmount(newAmount);
    }

    public void increaseProductAmount(Product product) {
        ShoppingCartItem shoppingCartItem = items.get(product);
        if (shoppingCartItem == null) {
            items.put(product, new ShoppingCartItem(product));
        } else {
            shoppingCartItem.increaseAmount();
        }

    }

    public void decreaseAmount(Product product) {
        ShoppingCartItem shoppingCartItem = items.get(product);
        if (shoppingCartItem != null) {
            if (shoppingCartItem.getAmount() == 1) {
                items.remove(shoppingCartItem);
            } else {
                shoppingCartItem.decreaseAmount();
            }

        }
    }

    public double getTotal() {
        double total = 0;
        for (ShoppingCartItem shoppingCartItem:items.values()
                ) {
            total += shoppingCartItem.getSum();
        }
        return total;
    }
}
