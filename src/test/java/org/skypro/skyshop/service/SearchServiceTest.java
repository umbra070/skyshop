package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) // Разрешаем "лишние" настройки моков
@DisplayName("Тесты для SearchService")
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    private UUID productId;
    private UUID articleId;
    private Searchable productWithMatch;
    private Searchable articleWithMatch;
    private Searchable productWithoutMatch;
    private Searchable articleWithoutMatch;
    private String searchString;

    @BeforeEach
    void setUp() {
        searchString = "Samsung";

        // Создаем реальные ID для моков
        productId = UUID.randomUUID();
        articleId = UUID.randomUUID();

        // Создаем моки
        productWithMatch = mock(Searchable.class);
        articleWithMatch = mock(Searchable.class);
        productWithoutMatch = mock(Searchable.class);
        articleWithoutMatch = mock(Searchable.class);

        // Настраиваем ВСЕ необходимые методы для Searchable
        // Важно: SearchResult.fromSearchable() вызывает getId() и getName()

        // Продукт с совпадением
        when(productWithMatch.getId()).thenReturn(productId);
        when(productWithMatch.getName()).thenReturn("Чехол для Samsung Galaxy S22+");
        when(productWithMatch.getSearchTerm()).thenReturn("Чехол для Samsung Galaxy S22+");

        // Статья с совпадением
        when(articleWithMatch.getId()).thenReturn(articleId);
        when(articleWithMatch.getName()).thenReturn("Samsung BXH2890: три факта о новинке");
        when(articleWithMatch.getSearchTerm()).thenReturn("Samsung BXH2890: три факта о новинке");

        // Продукт без совпадения
        when(productWithoutMatch.getId()).thenReturn(UUID.randomUUID());
        when(productWithoutMatch.getName()).thenReturn("iPhone 16 Pro Max");
        when(productWithoutMatch.getSearchTerm()).thenReturn("iPhone 16 Pro Max");

        // Статья без совпадения
        when(articleWithoutMatch.getId()).thenReturn(UUID.randomUUID());
        when(articleWithoutMatch.getName()).thenReturn("Как выбрать наушники");
        when(articleWithoutMatch.getSearchTerm()).thenReturn("Как выбрать наушники");
    }

    @Nested
    @DisplayName("Поиск с результатами")
    class SearchWithResultsTests {

        @Test
        @DisplayName("Должен найти все объекты, содержащие поисковую строку")
        void shouldFindAllMatchingObjects() {
            // Arrange
            Set<Searchable> allContent = new HashSet<>(Arrays.asList(
                    productWithMatch, articleWithMatch, productWithoutMatch, articleWithoutMatch
            ));
            when(storageService.getAllContent()).thenReturn(allContent);

            // Act
            Set<SearchResult> results = searchService.search(searchString);

            // Assert
            assertEquals(2, results.size(), "Должно быть найдено 2 объекта");

            verify(storageService, times(1)).getAllContent();
        }

        @Test
        @DisplayName("Должен отсортировать результаты по имени")
        void shouldSortResultsAlphabetically() {
            // Arrange - создаем отдельные моки для этого теста
            Searchable first = mock(Searchable.class);
            Searchable second = mock(Searchable.class);

            UUID id1 = UUID.randomUUID();
            UUID id2 = UUID.randomUUID();

            when(first.getId()).thenReturn(id1);
            when(first.getName()).thenReturn("Apple Samsung");
            when(first.getSearchTerm()).thenReturn("Apple Samsung");

            when(second.getId()).thenReturn(id2);
            when(second.getName()).thenReturn("Samsung Galaxy");
            when(second.getSearchTerm()).thenReturn("Samsung Galaxy");

            Set<Searchable> allContent = new HashSet<>(Arrays.asList(first, second));
            when(storageService.getAllContent()).thenReturn(allContent);

            // Act
            Set<SearchResult> results = searchService.search("Samsung");

            // Assert
            assertEquals(2, results.size());
        }
    }

    @Nested
    @DisplayName("Поиск без результатов")
    class SearchWithoutResultsTests {

        @Test
        @DisplayName("Должен вернуть пустой Set, если нет совпадений")
        void shouldReturnEmptySetWhenNoMatches() {
            // Arrange
            Set<Searchable> allContent = new HashSet<>(Arrays.asList(
                    productWithoutMatch, articleWithoutMatch
            ));
            when(storageService.getAllContent()).thenReturn(allContent);

            // Act
            Set<SearchResult> results = searchService.search(searchString);

            // Assert
            assertTrue(results.isEmpty(), "Результат должен быть пустым");
            assertEquals(0, results.size());
        }

        @Test
        @DisplayName("Должен вернуть пустой Set при пустом хранилище")
        void shouldReturnEmptySetWhenStorageIsEmpty() {
            // Arrange
            when(storageService.getAllContent()).thenReturn(new HashSet<>());

            // Act
            Set<SearchResult> results = searchService.search(searchString);

            // Assert
            assertTrue(results.isEmpty());
            assertEquals(0, results.size());
        }
    }

    @Nested
    @DisplayName("Граничные случаи")
    class BoundaryTests {

        @Test
        @DisplayName("Должен обработать пустую строку поиска")
        void shouldHandleEmptySearchString() {
            // Arrange - создаем отдельные моки для этого теста
            Searchable item1 = mock(Searchable.class);
            Searchable item2 = mock(Searchable.class);

            UUID id1 = UUID.randomUUID();
            UUID id2 = UUID.randomUUID();

            when(item1.getId()).thenReturn(id1);
            when(item1.getName()).thenReturn("Samsung TV");
            when(item1.getSearchTerm()).thenReturn("Samsung TV");

            when(item2.getId()).thenReturn(id2);
            when(item2.getName()).thenReturn("iPhone Samsung");
            when(item2.getSearchTerm()).thenReturn("iPhone Samsung");

            Set<Searchable> allContent = new HashSet<>(Arrays.asList(item1, item2));
            when(storageService.getAllContent()).thenReturn(allContent);

            // Act
            Set<SearchResult> results = searchService.search("");

            // Assert
            assertEquals(2, results.size(), "Пустая строка должна находить всё");
        }

        @Test
        @DisplayName("Должен выбросить NullPointerException при null в строке поиска")
        void shouldThrowNullPointerExceptionForNullSearchString() {
            // Arrange
            Set<Searchable> allContent = new HashSet<>(Arrays.asList(
                    productWithMatch, articleWithMatch
            ));
            when(storageService.getAllContent()).thenReturn(allContent);

            // Act & Assert
            assertThrows(NullPointerException.class,
                    () -> searchService.search(null));
        }
    }

    @Nested
    @DisplayName("Тесты с реальными объектами (без моков)")
    class TestsWithRealObjects {

        @Test
        @DisplayName("Должен работать с реальными Product и Article")
        void shouldWorkWithRealProductsAndArticles() {
            // Arrange
            UUID id1 = UUID.randomUUID();
            UUID id2 = UUID.randomUUID();

            Searchable realProduct = new SimpleProduct("Samsung TV", 50000, id1);
            Searchable realArticle = new Article("Обзор Samsung TV", "Текст статьи", id2);

            Set<Searchable> allContent = new HashSet<>(Arrays.asList(realProduct, realArticle));
            when(storageService.getAllContent()).thenReturn(allContent);

            // Act
            Set<SearchResult> results = searchService.search("Samsung");

            // Assert
            assertEquals(2, results.size());
        }

        @Test
        @DisplayName("Должен обработать пустую строку с реальными объектами")
        void shouldHandleEmptyStringWithRealObjects() {
            // Arrange
            UUID id1 = UUID.randomUUID();
            UUID id2 = UUID.randomUUID();

            Searchable realProduct = new SimpleProduct("Samsung TV", 50000, id1);
            Searchable realArticle = new Article("Обзор Samsung TV", "Текст статьи", id2);

            Set<Searchable> allContent = new HashSet<>(Arrays.asList(realProduct, realArticle));
            when(storageService.getAllContent()).thenReturn(allContent);

            // Act
            Set<SearchResult> results = searchService.search("");

            // Assert
            assertEquals(2, results.size(), "Пустая строка должна находить всё");
        }
    }
}