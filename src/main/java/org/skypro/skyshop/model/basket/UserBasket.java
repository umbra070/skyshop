package org.skypro.skyshop.model.basket;

import java.util.HashSet;
import java.util.Set;

public class UserBasket {
    private final Set<BasketItem> items = new HashSet<>();
    private final Integer totalPrice;

    public UserBasket(Set<BasketItem> items){
        this.items.addAll(items);
        totalPrice = items.stream()
                .mapToInt(p -> p.getProduct().getProductPrice())
                .sum();
    }
}
