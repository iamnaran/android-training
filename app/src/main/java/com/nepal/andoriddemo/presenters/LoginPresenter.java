package com.nepal.andoriddemo.presenters;


import android.util.Log;

import com.google.gson.GsonBuilder;
import com.nepal.andoriddemo.apiservices.ApiClient;
import com.nepal.andoriddemo.apiservices.LoginApiService;
import com.nepal.andoriddemo.models.UserLogin;
import com.nepal.andoriddemo.utils.Utilities;
import com.nepal.andoriddemo.utils.UtilitiesFunctions;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private WeakReference<View> view;

    public LoginPresenter(LoginPresenter.View view) {
        this.view = new WeakReference<>(view);

    }

    private LoginPresenter.View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    public interface View {

        void onLoginResponseSuccess(UserLogin userLogin);

        void onResponseFailure(String message);

    }

    public void userLogin(String email, String password) {


        LoginApiService loginApiService = ApiClient.getClient().create(LoginApiService.class);

        loginApiService.loginService(email, password).enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        Utilities.saveLoginResponse(response.body());
                        getView().onLoginResponseSuccess(response.body());

                    }

                }


            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {

                getView().onResponseFailure("Something went wrong!");

            }
        });

    }



}
