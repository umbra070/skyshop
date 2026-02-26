package org.skypro.skyshop.model.exception;

public class ShopException {
    private final String code;
    private final String message;

    public ShopException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
