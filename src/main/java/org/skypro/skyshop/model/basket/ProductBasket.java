package org.skypro.skyshop.model.basket;

import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> basket = new HashMap<>();

    public ProductBasket() {

    }

    public void setProductInBasket(UUID id) {
        basket.putIfAbsent(id, 1);
        basket.computeIfPresent(id, (uuid, i) -> i + 1);
    }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(basket);
    }
}
