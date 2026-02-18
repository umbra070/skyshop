package org.skypro.skyshop.model.search;

public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType){
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public String getContentType(){
        return contentType;
    }

    @Override
    public String toString(){
        return String.format("ID: %s || Name: %s || Content Type: %s", id, name, contentType);
    }

    public static SearchResult fromSearchable(Searchable searchable){
        return new SearchResult(searchable.getId().toString(), searchable.getName(), searchable.getContentType());
    }
}
