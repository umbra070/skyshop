package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@SessionScope
public class BasketService {
    private final ProductBasket basket;
    private final StorageService storage;

    public BasketService(ProductBasket basket, StorageService storage) {
        this.basket = basket;
        this.storage = storage;
    }

    public void addProductInBasketById(UUID id) {
        if (storage.getProductById(id).isEmpty()) {
            throw new NoSuchProductException("Такого товара в списке нет");
        }
        basket.setProductInBasket(id);
    }

    public UserBasket getUserBasket() {
        Set<BasketItem> userBasket;
        userBasket = basket.getBasket().keySet().stream()
                .peek(id -> {
                    if (storage.getProductById(id).isEmpty()) {
                        throw new NoSuchProductException(String.format("По данному id %s товара не обнаружено", id.toString()));
                    }
                })
                .map((id) -> new BasketItem(storage.getProductById(id).get(), basket.getBasket().get(id)))
                .collect(Collectors.toSet());
        return new UserBasket(userBasket);
    }
}
