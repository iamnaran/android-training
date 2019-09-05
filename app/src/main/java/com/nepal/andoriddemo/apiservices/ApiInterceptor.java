package com.nepal.andoriddemo.apiservices;

import android.text.TextUtils;


import com.nepal.andoriddemo.utils.Utilities;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by NaRan on 23,July,2018.
 * Copyright (c) UT Pvt. Ltd. All rights reserved.
 * nrn.panthi@gmail.com
 * MacBook
 **/

public class ApiInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        if (Utilities.getLoginResponse() == null ) {
//            return chain.proceed(originalRequest);
//        }

        Request request = originalRequest.newBuilder()
                .addHeader("Authorization", Utilities.getLoginResponse().getUserDetails().getToken())
                .addHeader("Accept", "Accept: application/x.school.v1+json")
                .header("Cache-Control", String.format("max-age=%d", 50000))
                .build();
        return chain.proceed(request);
    }
}



//    Request request = chain.request();
//
//
//        if (Utilities.getAccessToken() == null || TextUtils.isEmpty(Utilities.getAccessToken())) {
//                return chain.proceed(request);
//                }
//
//                request = request.newBuilder()
//                .addHeader("Authorization", Utilities.getAccessToken())
//                .addHeader("Accept", "Accept: application/x.school.v1+json")
//                .header("Cache-Control", String.format("max-age=%d", 50000))
//                .build();
//
//                Response response = chain.proceed(request);
//
//                if (response.code() == 403) {
//
//                Utilities.saveSessionExpiredData(true);
//                Utilities.logoutAll();
//
//                }
//
//                return response;
//
//                }