package com.depromeet.android.login.model;

import com.depromeet.android.data.ResponseAuth;

public interface LoginCallback {
    interface RetrofitCallback {
        void onSuccess(int code, ResponseAuth responseAuth);
        void onFailure();
    }
}
