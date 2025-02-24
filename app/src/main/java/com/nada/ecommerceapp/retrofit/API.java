package com.nada.ecommerceapp.retrofit;

import com.nada.ecommerceapp.repo.remoteData.CategoriesModel;
import com.nada.ecommerceapp.util.ProductsModel;
import com.nada.ecommerceapp.repo.remoteData.UsersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    String BASE_URL = "https://api.escuelajs.co/api/v1/";

    @GET("products")
    Call<List<ProductsModel>> getProducts();
    @GET("categories")
    Call<List<CategoriesModel>> getCategories();
    @GET("users")
    Call<List<UsersModel>> getUsers();
}
