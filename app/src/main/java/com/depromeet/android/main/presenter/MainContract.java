package com.depromeet.android.main.presenter;

import com.depromeet.android.data.Category;
import com.depromeet.android.main.adapter.MainAdapterContract;

public interface MainContract {
    interface View {
        void toast(String msg);
        void startInputActivity(Category category);
    }

    interface Presenter {
        void getCategories();
        void attachView(View view);
        void detachView();
        void setMainAdapterView(MainAdapterContract.View adapterView);
        void setMainAdapterModel(MainAdapterContract.Model adapterModel);
    }
}
