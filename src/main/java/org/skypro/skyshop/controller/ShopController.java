package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.springframework.web.bind.annotation.*;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@RestController
public class ShopController {
    StorageService storageService;
    SearchService searchService;
    BasketService basketService;

    public ShopController() {
        storageService = new StorageService();
        searchService = new SearchService(storageService);
        basketService = new BasketService(new ProductBasket(), storageService);
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getProducts().values();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getArticles().values();
    }

    @GetMapping("/search")
    public Set<SearchResult> getSearchResalt(@RequestParam("pattern") String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProductToBasket(@PathVariable("id") UUID id) {
        basketService.addProductInBasketById(id);
        return "Продукт успешно добавлен";
    }

    @ResponseBody
    @GetMapping("/basket")
    public UserBasket getBasket() {
        return basketService.getUserBasket();
    }
}
