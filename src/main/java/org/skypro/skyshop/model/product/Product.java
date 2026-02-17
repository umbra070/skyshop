

package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

//Модель, описывающая продукт
public abstract class Product implements Searchable {
    private final UUID id;
    private final String productName;

    public Product(String productName, UUID id) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
        this.productName = productName;
        this.id = id;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getSearchTerm() {
        return productName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return this.productName.equals(((Product) obj).productName);
    }

    @Override
    public int hashCode() {
        return this.productName.hashCode();
    }

    public abstract boolean isSpecial();

    public abstract int getProductPrice();

    @Override
    public String getName() {
        return productName;
    }
}
