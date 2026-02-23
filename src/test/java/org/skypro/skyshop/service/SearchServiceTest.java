package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
//import org.testng.annotations.BeforeMethod;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService mockStorageService;

    @InjectMocks
    SearchService searchTest;

    @Test
    void search_normalReturn() {
        searchTest = new SearchService(mockStorageService);
        String searchString = "Samsung";

        Product testProduct = mock(SimpleProduct.class);
        Article testArticle = mock(Article.class);

        when(testProduct.getId()).thenReturn(UUID.randomUUID());
        when(testProduct.getName()).thenReturn("Чехол для Samsung Galaxy S22+");
        when(testProduct.getContentType()).thenReturn("PRODUCT");
        when(testProduct.getSearchTerm()).thenReturn("Чехол для Samsung Galaxy S22+");

        when(testArticle.getId()).thenReturn(UUID.randomUUID());
        when(testArticle.getName()).thenReturn("Samsung BXH2890: три факта о новинке");
        when(testArticle.getSearchTerm()).thenReturn("Samsung BXH2890: три факта о новинке");

        Set<Searchable> allContent = new HashSet<>();
        allContent.add(testArticle);
        allContent.add(testProduct);

        when(mockStorageService.getAllContent()).thenReturn(allContent);

        Set<SearchResult> result = searchTest.search(searchString);

        System.out.println("Строка для поиска в статье: " + testArticle.getSearchTerm());
        System.out.println("Строка для поиска в товаре" + testProduct.getSearchTerm());
        System.out.println("Количество объектов в хранилище данных: " + mockStorageService.getAllContent().size());
        System.out.println("Проверка вхождения поисковой строки в строку статьи: " + testArticle.getSearchTerm().contains(searchString));
        System.out.println("Проверка вхождения строки поиска в строку товара: " + testProduct.getSearchTerm().contains(searchString));
        System.out.println("Проверка количества тестовой коллекции данных:" + allContent.size());
        System.out.println(allContent.stream()
                .filter(s -> s.getSearchTerm().contains(searchString))
                .count());
        System.out.println("Количестве возвращенных результатов поиска: " + result.size());
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(r -> r.getName().contains(searchString)));
    }

    @Test
    void search_emptyStorage(){
        Set<Searchable> allContent = new HashSet<>();
        searchTest = new SearchService(mockStorageService);
        String searchString = "Samsung";
        when(mockStorageService.getAllContent()).thenReturn(allContent);

        Set<SearchResult> result = searchTest.search(searchString);

        assertEquals(0, result.size());
        assertFalse(result.stream().anyMatch(r -> r.getName().contains(searchString)));
    }

    @Test
    void search_dataDoesNotContainsSearchString(){
        searchTest = new SearchService(mockStorageService);
        String searchString = "iPhone";

        Product testProduct = mock(SimpleProduct.class);
        Article testArticle = mock(Article.class);

        when(testProduct.getSearchTerm()).thenReturn("Чехол для Samsung Galaxy S22+");

        when(testArticle.getSearchTerm()).thenReturn("Samsung BXH2890: три факта о новинке");

        Set<Searchable> allContent = new HashSet<>();
        allContent.add(testArticle);
        allContent.add(testProduct);

        when(mockStorageService.getAllContent()).thenReturn(allContent);

        Set<SearchResult> result = searchTest.search(searchString);

        assertEquals(0, result.size());
        assertFalse(result.stream().anyMatch(r -> r.getName().contains(searchString)));
    }
}
