package com.nada.ecommerceapp.repo.remoteData;

public class CategoriesModel {
    private String id;
    private String name;
    private String imageUrl;
    String keyLoremSpace ;

    public CategoriesModel(String id, String name, String imageUrl, String keyLoremSpace) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.keyLoremSpace = keyLoremSpace;
    }

    public String getKeyLoremSpace() {
        return keyLoremSpace;
    }

    public void setKeyLoremSpace(String keyLoremSpace) {
        this.keyLoremSpace = keyLoremSpace;
    }


    public CategoriesModel() {
    }

    public CategoriesModel(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
