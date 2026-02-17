package org.skypro.skyshop.service;

import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final StorageService storage;

    public SearchService(){
        storage = new StorageService();
    }
}
