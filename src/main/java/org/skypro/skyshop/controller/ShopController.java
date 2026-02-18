package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.Set;

@RestController
public class ShopController {
    StorageService storageService;
    SearchService searchService;
    public ShopController(){
        storageService = new StorageService();
        searchService= new SearchService();
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts(){
        return storageService.getProducts().values();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles(){
        return storageService.getArticles().values();
    }

    @GetMapping("/search")
    public Set<SearchResult> getSearchResalt(@RequestParam("pattern") String pattern){
        return searchService.search(pattern);
    }
}
