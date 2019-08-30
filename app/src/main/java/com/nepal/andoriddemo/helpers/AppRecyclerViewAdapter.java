package com.nepal.andoriddemo.helpers;


import androidx.recyclerview.widget.RecyclerView;

public abstract class AppRecyclerViewAdapter extends RecyclerView.Adapter {

    public abstract void add(Object object);
    public abstract void clear();

}
