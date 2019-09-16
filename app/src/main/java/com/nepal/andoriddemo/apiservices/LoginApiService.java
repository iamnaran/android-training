package com.nepal.andoriddemo.apiservices;


import com.nepal.andoriddemo.models.UserLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by NaRan on 5, March, 2019.
 * Copyright (c) UT Pvt. Ltd. All rights reserved.
 * nrn.panthi@gmail.com
 * MacBook
 **/
public interface LoginApiService {

    @POST("login")
    @FormUrlEncoded
    Call<UserLogin> loginService(
            @Field("email") String email,
            @Field("password") String password
    );


}
