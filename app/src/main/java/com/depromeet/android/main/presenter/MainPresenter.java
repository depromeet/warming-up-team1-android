package com.depromeet.android.main.presenter;

import android.print.PrinterId;

import com.depromeet.android.data.Category;
import com.depromeet.android.main.adapter.MainAdapterContract;
import com.depromeet.android.main.adapter.OnItemClickListener;

public class MainPresenter implements MainContract.Presenter, OnItemClickListener {
    private MainContract.View view;
    private MainAdapterContract.View adapterView;
    private MainAdapterContract.Model adapterModel;

    public MainPresenter() {
    }

    @Override
    public void getCategories() {
        // retrofit 추가
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void setMainAdapterView(MainAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnClickListener(this);
    }

    @Override
    public void setMainAdapterModel(MainAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onItemClick(Category category) {
        view.startInputActivity(category);
    }
}