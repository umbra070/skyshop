package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    StorageService mockStorageService;
    @InjectMocks
    SearchService searchTest;

    void SetUpVoidStorageTesting(){
        mockStorageService = new StorageService(new HashMap<>(), new HashMap<>());
        searchTest = new SearchService(mockStorageService);
    }

    void testingEmptyDataSource(){
        String testString = "Samsung";
        Mockito.when(mockStorageService.getAllContent(testString)).thenReturn().
    }
}
