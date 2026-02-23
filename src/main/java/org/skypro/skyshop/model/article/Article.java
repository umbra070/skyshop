package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String articleName;
    private final String articleText;

    public Article(String articleName, String articleText, UUID id) {
        this.articleName = articleName;
        this.articleText = articleText;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s\n\r%s", articleName, articleText);
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return this.toString();
    }

    @Override
    public String getName() {
        return articleName;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public int hashCode() {
        return this.articleName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return this.articleName.equals(((Article) obj).articleName);
    }

    @Override
    public UUID getId(){
        return id;
    }
}
