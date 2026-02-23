package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StorageServiceTest {

    @Test
    void getProductById_TestingNormalReturn() {
        StorageService storageTest = new StorageService();
        UUID id = storageTest.getProducts().keySet().iterator().next();
        Optional<Product> result = storageTest.getProductById(id);
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void getProductById_TestingNotRealUUID() {
        StorageService storageTest = new StorageService();
        UUID testingId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        Optional<Product> result = storageTest.getProductById(testingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void getAllContent_TestingReturnContent() {
        StorageService storageTest = new StorageService();
        Set<Searchable> dataStorage = storageTest.getAllContent();
        assertFalse(dataStorage.isEmpty());
    }
}
