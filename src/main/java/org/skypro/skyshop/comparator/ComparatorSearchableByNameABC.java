package org.skypro.skyshop.comparator;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Comparator;

public class ComparatorSearchableByNameABC implements Comparator<Searchable> {
    @Override
    public int compare(Searchable s1, Searchable s2) {
        return s1.getName().compareTo(s2.getName());
    }
}
