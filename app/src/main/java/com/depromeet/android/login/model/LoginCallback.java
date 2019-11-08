package com.depromeet.android.login.model;

import com.depromeet.android.data.ResponseAuth;
import com.depromeet.android.data.ResponseConnectMem;

public interface LoginCallback {
    interface RetrofitCallback {
        void onSuccess(int code, ResponseAuth responseAuth);
        void onFailure();
    }
    interface RetrofitCallbackConnect {
        void onSuccess(int code, ResponseConnectMem responseConnectMem);
        void onFailure();
    }
}
