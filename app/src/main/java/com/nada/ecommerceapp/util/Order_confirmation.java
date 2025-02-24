package com.nada.ecommerceapp.util;

public class Order_confirmation {
    private int  userID  ,cartID ;
    private  double Totalprice;
    private  String Country , City , Street , email, phone_number , zip_code ;
    CartElement[] elements;



    public Order_confirmation(int userID, int cartID, String phone_number, String zip_code, double totalprice, String country, String city, String street, String email, CartElement[] elements) {
        this.userID = userID;
        this.phone_number = phone_number;
        this.zip_code = zip_code;
        Totalprice = totalprice;
        Country = country;
        City = city;
        Street = street;
        this.email = email;
        this.elements = elements;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public double getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(double totalprice) {
        Totalprice = totalprice;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CartElement[] getElements() {
        return elements;
    }

    public void setElements(CartElement[] elements) {
        this.elements = elements;
    }
}
