package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

public class BasketItem {
    private final Product product;
    private final Integer count;

    public BasketItem(Product product, Integer count){
        this.product = product;
        this.count = count;
    }
    public Product getProduct(){
        return product;
    }

    public Integer getCount(){
        return count;
    }
}
