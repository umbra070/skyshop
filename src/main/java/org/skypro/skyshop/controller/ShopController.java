package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;

@RestController
public class ShopController {
    StorageService storageService = new StorageService();
    public ShopController(){

    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts(){
        return storageService.getProducts().values();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles(){
        return storageService.getArticles().values();
    }
}
