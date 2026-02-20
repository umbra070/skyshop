package org.skypro.skyshop.model.basket;

import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> basket = new HashMap<>();

    public ProductBasket() {

    }

    public void setProductInBasket(UUID id) {
        if (basket.containsKey(id)) {
            basket.put(id, basket.get(id) + 1);
        } else {
            basket.put(id, 1);
        }
    }

    public Map<UUID, Integer> getBasket(){
        return Collections.unmodifiableMap(basket);
    }
}
