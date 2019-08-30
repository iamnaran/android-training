package com.nepal.andoriddemo.apiservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nepal.andoriddemo.BuildConfig;
import com.nepal.andoriddemo.helpers.MyApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NaRan on 23,July,2018.
 * Copyright (c) UT Pvt. Ltd. All rights reserved.
 * nrn.panthi@gmail.com
 * MacBook
 **/

public class ApiClient {

    private static final String BASE_URL = "https://elibrary.smartgov.app/api/";
    public static final String IMAGE_BASE_URL = "";

    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        builder.interceptors().add(interceptor);

        OkHttpClient httpClient = builder
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new ApiInterceptor())
                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        RxJava2CallAdapterFactory rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.computation());


        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(rxJava2CallAdapterFactory)
                    .client(httpClient)
                    .build();
        }
        return retrofit;
    }

}
