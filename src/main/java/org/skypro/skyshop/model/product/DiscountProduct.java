package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountProduct extends Product {
    private final int productPrice;
    private final int discount;

    public DiscountProduct(String productName, int productPrice, int discount, UUID id) {
        super(productName, id);
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException(String.format("Процент скидки продукта %s должен быть в диапазоне между 0 и 100", productName));
        }
        if (productPrice <= 0) {
            throw new IllegalArgumentException(String.format("Стоимость продукта %s должна быть строго больше 0", productName));
        }
        this.productPrice = productPrice;
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return String.format("%s : %d(%d%%)", this.getSearchTerm(), this.getProductPrice(), this.getDiscount());
    }

    @Override
    public int getProductPrice() {
        return productPrice - (productPrice * discount / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
