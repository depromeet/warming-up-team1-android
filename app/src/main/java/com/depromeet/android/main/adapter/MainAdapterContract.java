package com.depromeet.android.main.adapter;

import java.util.ArrayList;

public interface MainAdapterContract {
    interface View {
        void notifyAdapter();
    }

    interface Model {
        void addCategory(ArrayList items);
        void clearCategory();
    }
}
