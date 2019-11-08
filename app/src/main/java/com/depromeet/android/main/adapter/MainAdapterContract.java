package com.depromeet.android.main.adapter;

import java.util.ArrayList;

public interface MainAdapterContract {
    interface View {
        void notifyAdapter();
        void setOnClickListener(OnItemClickListener onClickListener);
        void setOnDragEnterListener(OnItemDragEnterListener onDragEnterListener);
    }

    interface Model {
        void addCategory(ArrayList items);
        void clearCategory();
    }
}
