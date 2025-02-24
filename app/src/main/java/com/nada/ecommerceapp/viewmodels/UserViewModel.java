package com.nada.ecommerceapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nada.ecommerceapp.repo.remoteData.UsersModel;
import com.nada.ecommerceapp.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<UsersModel>> userList;


    public MutableLiveData<List<UsersModel>> getUser(){

        if(userList == null){
            userList = new MutableLiveData<>();
            loadUsers();
        }

        return userList;

    }
    private void loadUsers(){

        Call<List<UsersModel>> call = RetrofitClient.getInstance().getMyApi().getUsers();
        call.enqueue(new Callback<List<UsersModel>>() {
            @Override
            public void onResponse(Call<List<UsersModel>> call, Response<List<UsersModel>> response) {
                if(response.isSuccessful()){
                    userList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<UsersModel>> call, Throwable t) {
//                Toast.makeText(ctx, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
