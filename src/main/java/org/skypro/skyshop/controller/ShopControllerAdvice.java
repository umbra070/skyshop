package org.skypro.skyshop.controller;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.exception.ShopException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopException> noSuchProductHandler(NoSuchProductException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ShopException("100", "Товар не найден"));
    }
}
