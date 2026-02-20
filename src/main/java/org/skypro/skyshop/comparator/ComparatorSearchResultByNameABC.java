package org.skypro.skyshop.comparator;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Comparator;

public class ComparatorSearchResultByNameABC implements Comparator<SearchResult> {
    @Override
    public int compare(SearchResult sr1, SearchResult sr2) {
        return sr1.getName().compareTo(sr2.getName());
    }
}
