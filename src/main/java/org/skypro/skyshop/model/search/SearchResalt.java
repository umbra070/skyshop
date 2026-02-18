package org.skypro.skyshop.model.search;

public class SearchResalt {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResalt(String id, String name, String contentType){
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

    public static SearchResalt fromSearchable(Searchable searchable){
        return new SearchResalt(searchable.getId().toString(), searchable.getName(), searchable.getContentType());
    }
}
