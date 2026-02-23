package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.UUID;

public class StorageServiceTest {
    StorageService storageTest = new StorageService();

    @Test
    void getProductByIdTestingNotRealUUID() {
        UUID testingId = UUID.fromString("c134c95c-56d4-4eb8-a456-55a34565abab");
        Assertions.assertThrows(NoSuchProductException.class, () -> storageTest.getProductById(testingId)).printStackTrace();
    }

    @Test
    void getProductByID_TestingNullUUID() {
        UUID testingId = null;
        Assertions.assertThrows(NoSuchProductException.class, () -> storageTest.getProductById(testingId)).printStackTrace();
    }
}
