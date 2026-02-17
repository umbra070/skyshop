package model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int productPrice;

    public SimpleProduct(String productName, int productPrice, UUID id) {
        super(productName, id);
        if (productPrice <= 0) {
            throw new IllegalArgumentException(String.format("Стоимость продукта %s должна быть строго больше нуля", productName));
        }
        this.productPrice = productPrice;

    }

    @Override
    public String toString() {
        return String.format("%s : %d", this.getSearchTerm(), this.getProductPrice());
    }

    @Override
    public int getProductPrice() {
        return productPrice;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
