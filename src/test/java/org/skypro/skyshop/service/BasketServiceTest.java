package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasketMock;

    @Mock
    private StorageService storageServiceMock;

    @InjectMocks
    private BasketService basketService;

    private UUID existingProductId;
    private UUID anotherProductId;
    private UUID nonExistentProductId;
    private Product testProduct;
    private Product anotherTestProduct;
    private Map<UUID, Integer> basketContent;

    @BeforeEach
    void setUp() {
        existingProductId = UUID.randomUUID();
        anotherProductId = UUID.randomUUID();
        nonExistentProductId = UUID.randomUUID();

        testProduct = new SimpleProduct("Тестовый продукт1", 1000, existingProductId);
        anotherTestProduct = new SimpleProduct("Другой продукт2", 500, anotherProductId);

        basketContent = new HashMap<>();
    }

    @Nested
    @DisplayName("Тесты метода addProductInBasketById")
    class AddProductInBasketByIdTests {

        @Test
        @DisplayName("Должен вызвать setProductInBasket у корзины при добавлении существующего продукта")
        void shouldCallSetProductInBasketWhenProductExists() {
            // Arrange
            when(storageServiceMock.getProductById(existingProductId))
                    .thenReturn(Optional.of(testProduct));

            // Act
            basketService.addProductInBasketById(existingProductId);

            // Assert
            verify(productBasketMock, times(1)).setProductInBasket(existingProductId);
            verify(storageServiceMock, times(1)).getProductById(existingProductId);
        }

        @Test
        @DisplayName("Должен вызвать setProductInBasket несколько раз при многократном добавлении")
        void shouldCallSetProductInBasketMultipleTimes() {
            // Arrange
            when(storageServiceMock.getProductById(existingProductId))
                    .thenReturn(Optional.of(testProduct));

            // Act
            basketService.addProductInBasketById(existingProductId);
            basketService.addProductInBasketById(existingProductId);
            basketService.addProductInBasketById(existingProductId);

            // Assert
            verify(productBasketMock, times(3)).setProductInBasket(existingProductId);
        }

        @Test
        @DisplayName("Должен вызвать setProductInBasket для разных продуктов")
        void shouldCallSetProductInBasketForDifferentProducts() {
            // Arrange
            when(storageServiceMock.getProductById(existingProductId))
                    .thenReturn(Optional.of(testProduct));
            when(storageServiceMock.getProductById(anotherProductId))
                    .thenReturn(Optional.of(anotherTestProduct));

            // Act
            basketService.addProductInBasketById(existingProductId);
            basketService.addProductInBasketById(anotherProductId);
            basketService.addProductInBasketById(existingProductId);

            // Assert
            verify(productBasketMock, times(2)).setProductInBasket(existingProductId);
            verify(productBasketMock, times(1)).setProductInBasket(anotherProductId);
        }

        @Test
        @DisplayName("Должен выбросить исключение при попытке добавить несуществующий продукт")
        void shouldThrowExceptionWhenProductDoesNotExist() {
            // Arrange
            when(storageServiceMock.getProductById(nonExistentProductId))
                    .thenReturn(Optional.empty());

            // Act & Assert
            NoSuchProductException exception = assertThrows(
                    NoSuchProductException.class,
                    () -> basketService.addProductInBasketById(nonExistentProductId)
            );

            assertEquals("Такого товара в списке нет", exception.getMessage());
            verify(productBasketMock, never()).setProductInBasket(any(UUID.class));
        }

        @Test
        @DisplayName("Не должен вызывать setProductInBasket при отсутствии продукта")
        void shouldNotCallSetProductInBasketWhenProductNotFound() {
            // Arrange
            when(storageServiceMock.getProductById(nonExistentProductId))
                    .thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(NoSuchProductException.class,
                    () -> basketService.addProductInBasketById(nonExistentProductId));

            verify(productBasketMock, never()).setProductInBasket(any());
        }
    }
}
