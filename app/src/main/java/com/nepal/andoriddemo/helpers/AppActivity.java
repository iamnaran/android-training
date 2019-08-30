package com.nepal.andoriddemo.helpers;


import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by NaRan on 23,Sep,2018.
 * Copyright (c) All rights reserved.
 * nrn.panthi@gmail.com
 **/

public abstract class AppActivity extends AppCompatActivity {
    abstract protected void initializeView();
    abstract protected void initializeListeners();
}
