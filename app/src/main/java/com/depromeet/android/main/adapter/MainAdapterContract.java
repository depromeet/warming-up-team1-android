package com.depromeet.android.main.adapter;

import java.util.ArrayList;

public interface MainAdapterContract {
    interface View {
        void notifyAdapter();
        void setOnClickListener(OnItemClickListener onClickListener);
    }

    interface Model {
        void addCategory(ArrayList items);
        void clearCategory();
    }
}
