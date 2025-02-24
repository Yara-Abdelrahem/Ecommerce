package com.nada.ecommerceapp.util;

import android.widget.Button;

public class CartElement {
       private String name, details ,image;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(int product_ID) {
        Product_ID = product_ID;
    }

    public int getProduct_count() {
        return Product_count;
    }

    public void setProduct_count(int product_count) {
        Product_count = product_count;
    }

//    public int getID() {
//        return ID;
//    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getProduct_Price() {
        return Product_Price;
    }

    public void setProduct_Price(double product_Price) {
        Product_Price = product_Price;
    }
    public double getProduct_total() {
        return Product_total;
    }

    public void setProduct_total(double product_total) {
        Product_total = product_total;
    }
    private int cartID,userID , Product_ID,Product_count , ID;
       private double Product_Price, Product_total;
//        public CartElement(String name, String details, String image) {
//            this.name = name;
//            this.details = details;
//            this.image = image;
//        }
    public CartElement( int cartID , String name, int Product_ID, int Product_count,double Product_Price ,double Product_total , int userID, String image) {

        this.cartID = cartID;
        this.name = name;
        this.Product_ID = Product_ID;
        this.Product_count = Product_count;
        this.Product_Price = Product_Price;
        this.image = image;
        this.userID = userID;
        this.Product_total = Product_total;

    }

}
