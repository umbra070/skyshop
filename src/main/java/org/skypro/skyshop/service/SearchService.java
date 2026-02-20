package org.skypro.skyshop.service;

import org.skypro.skyshop.comparator.ComparatorSearchResultByNameABC;
import org.skypro.skyshop.comparator.ComparatorSearchableByNameABC;
import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Set<SearchResult> search(String searchString) {
        return storageService.getAllContent().stream()
                .filter(s -> s.getSearchTerm().contains(searchString))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toCollection(() -> new TreeSet<>(new ComparatorSearchResultByNameABC())));
    }
}
