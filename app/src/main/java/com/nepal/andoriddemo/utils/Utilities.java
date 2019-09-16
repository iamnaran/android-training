package com.nepal.andoriddemo.utils;


import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;
import com.nepal.andoriddemo.constants.AppConstants;
import com.nepal.andoriddemo.models.Notifications;
import com.nepal.andoriddemo.models.UserLogin;

import static com.nepal.andoriddemo.helpers.MyApplication.getSharedPreference;

/**
 * Created by NaRan on 23,Sep,2018.
 * Copyright (c) All rights reserved.
 * nrn.panthi@gmail.com
 **/

public class Utilities {

    /*
     * SHARED PREFERENCES UTILITIES
     * */

    public static void saveLoginResponse(UserLogin loginResponse) {

        String json = new GsonBuilder().create().toJson(loginResponse);

        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(AppConstants.LOGIN_RESPONSE, json);
        editor.apply();
        setIsFirstLogin(true);

    }


    public static UserLogin getLoginResponse() {
        String savedUserResponse = getSharedPreference().getString(AppConstants.LOGIN_RESPONSE, null);
        return new GsonBuilder().create().fromJson(savedUserResponse, UserLogin.class);
    }


    public static void setIsFirstLogin(boolean status) {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putBoolean(AppConstants.IS_FIRST_LOGIN, status);
        editor.apply();
    }

    public static boolean isLogin() {

        return getSharedPreference().getBoolean(AppConstants.IS_FIRST_LOGIN, false);
    }


    /**
     * @param notifications save & get
     */
    public static void saveNotificationData(Notifications notifications) {
        String json = new GsonBuilder().create().toJson(notifications);
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(AppConstants.NOTIFICATION_RESPONSE, json);
        editor.apply();

    }


    public static Notifications getNotificationResponse() {
        String savedUserResponse = getSharedPreference().getString(AppConstants.NOTIFICATION_RESPONSE, null);
        return new GsonBuilder().create().fromJson(savedUserResponse, Notifications.class);
    }


    public static void saveFcmToken(String fcmToken) {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(AppConstants.FCM_TOKEN, fcmToken);
        editor.apply();
    }

    public static String getFcmToken() {
        return getSharedPreference().getString(AppConstants.FCM_TOKEN, "");
    }


//    public static void saveLoginResponse(UserLogin loginResponse) {
//        String json = new GsonBuilder().create().toJson(loginResponse);
//        SharedPreferences.Editor editor = getSharedPreference().edit();
//        editor.putString(AppConstants.LOGIN_RESPONSE, json);
//        editor.apply();
//        setIsFirstLogin(true);
//    }


//    public static boolean isLogin() {
//        return MyApplication.getSharedPreference().getBoolean(AppConstants.IS_FIRST_LOGIN, false);
//    }

//
//    public static void saveVenueName(String venueName) {
//        SharedPreferences.Editor editor = getSharedPreference().edit();
//        editor.putString(AppConstants.VENUE_NAME, venueName);
//        editor.apply();
//    }
//
//    public static String getVenueName() {
//        return getSharedPreference().getString(AppConstants.VENUE_NAME, null);
//    }


//    public static void logout() {
//
//        SharedPreferences.Editor editor = getSharedPreference().edit();
//        editor.putString(AppConstants.LOGIN_RESPONSE, "");
//        editor.putBoolean(AppConstants.IS_FIRST_LOGIN, false);
//        editor.apply();
//
//    }

}
