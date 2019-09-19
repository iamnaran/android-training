package com.nepal.andoriddemo.presenters;


import com.nepal.andoriddemo.apiservices.ApiClient;
import com.nepal.andoriddemo.apiservices.HomeApiService;
import com.nepal.andoriddemo.apiservices.LoginApiService;
import com.nepal.andoriddemo.models.HomeModel;
import com.nepal.andoriddemo.models.UserLogin;
import com.nepal.andoriddemo.utils.Utilities;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private WeakReference<View> view;

    public HomePresenter(HomePresenter.View view) {
        this.view = new WeakReference<>(view);

    }

    private HomePresenter.View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    public interface View {

        void onHomeResponseSuccess(HomeModel homeModel);

        void onResponseFailure(String message);

    }

    public void homeApi() {


        HomeApiService homeApiService = ApiClient.getClient().create(HomeApiService.class);

        homeApiService.getHomeData().enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        getView().onHomeResponseSuccess(response.body());

                    }

                }


            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {

                getView().onResponseFailure("Something went wrong!");

            }
        });

    }



}
