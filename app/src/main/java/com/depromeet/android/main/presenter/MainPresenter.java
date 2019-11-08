package com.depromeet.android.main.presenter;

import com.depromeet.android.main.adapter.MainAdapterContract;
import com.depromeet.android.main.adapter.OnItemClickListener;
import com.depromeet.android.main.adapter.OnItemDragEnterListener;

public class MainPresenter implements MainContract.Presenter,
        OnItemDragEnterListener, OnItemClickListener {
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
        this.adapterView.setOnDragEnterListener(this);
    }

    @Override
    public void setMainAdapterModel(MainAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onItemDragResult() {
        view.startInputActivity();
    }

    @Override
    public void onItemClick() {
        view.startAddCategoryActivity();
    }
}