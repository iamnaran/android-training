package com.nepal.andoriddemo.helpers;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.PreferenceManager;

/**
 * Created by NaRan on 23,Sep,2018.
 * Copyright (c) All rights reserved.
 * nrn.panthi@gmail.com
 **/

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static MyApplication myApplication;
    private static SharedPreferences sharedPreferences;
    private static boolean isActive;


    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        myApplication = this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getAppContext());
        initialiseSocialLogin();

    }

    private void initialiseSocialLogin() {
//        FacebookSdk.isInitialized();
    }

    public static boolean isActivityVisible() {
        return isActive;
    }

    public static Context getAppContext() {
        return myApplication.getApplicationContext();
    }

    public static SharedPreferences getSharedPreference() {
        return sharedPreferences;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
