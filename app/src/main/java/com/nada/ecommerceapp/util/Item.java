package com.nada.ecommerceapp.util;


public class Item {
    private String name;
    private String description;
    private String price;
    private String imageUrl;

    // ... Add other properties ...

    public Item(String name,String description, String price, String imageUrl) {
        this.name = name;
        this.description = description ;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    public Item() {

    }


    public String getName()

    {
        return name;
    }

    public  String getPrice()

    {
        return price;
    }

    public String getImageUrl()

    {
        return imageUrl;
    }
    public String getDescription()

    {
        return description;
    }
}
