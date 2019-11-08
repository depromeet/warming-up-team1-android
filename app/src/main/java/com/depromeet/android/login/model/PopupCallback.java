package com.depromeet.android.login.model;


public interface PopupCallback {
    interface RetrofitCallback {
        void onSuccess(int code, String responseConnectKey);
        void onFailure();
    }
}
