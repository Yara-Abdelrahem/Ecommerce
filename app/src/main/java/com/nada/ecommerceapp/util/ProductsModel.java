package com.nada.ecommerceapp.util;

public class ProductsModel {

    private String name ,price , description ;
    private String images ;
    private String pId,amount;


    public ProductsModel(String pId, String amount, String name, String description, String price,String images) {
        this.pId = pId;
        this.amount = amount;
        this.name = name;
        this.description = description;
        this.price = price;
        this.images = images ;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
