package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;

import java.util.UUID;

public class BasketService {
    private final ProductBasket basket;
    private final StorageService storage;

    public BasketService(ProductBasket basket, StorageService storage){
        this.basket = basket;
        this.storage = storage;
    }

    public void addProductInBasketById(UUID id){
        storage.getProductById(id).ifPresentOrElse(p ->basket.setProductInBasket(p.getId()),() -> new IllegalArgumentException("Такого товара в списке нет"));
    }

    public UserBasket getUserBasket(){
        userBasket = new UserBasket();
    }
}
