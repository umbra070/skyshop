package model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    final static int PRODUCT_PRICE = 1999;

    public FixPriceProduct(String productName, UUID id) {
        super(productName, id);
    }

    @Override
    public String toString() {
        return String.format("%s : Фиксированная цена %d", this.getName(), this.getProductPrice());
    }

    @Override
    public int getProductPrice() {
        return PRODUCT_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
