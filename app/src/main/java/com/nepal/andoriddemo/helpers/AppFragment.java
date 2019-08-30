package com.nepal.andoriddemo.helpers;

import android.view.View;

import androidx.fragment.app.Fragment;

/**
 * Created by NaRan on 01,May,2018.
 * Copyright (c) All rights reserved.
 * nrn.panthi@gmail.com
 **/

public abstract class AppFragment extends Fragment {

    abstract protected void initializeView(View view);

    abstract protected void initializeListeners();

}
