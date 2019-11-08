package com.depromeet.android.login.model;

import com.depromeet.android.retrofit.ResponseCode;
import com.depromeet.android.retrofit.RetrofitService;
import com.depromeet.android.retrofit.RetrofitServiceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopupRetrofitModel {
    private RetrofitService retrofitService;
    private PopupCallback.RetrofitCallback callback;

    public PopupRetrofitModel() {
        retrofitService = RetrofitServiceManager.getRetrofitInstance();

    }

    public void setCallback(PopupCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

    public void getConnectKey(String userToken, int mid) {
        Call<String> call = retrofitService.getConnectKey("Bearer "+userToken, mid);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onSuccess(ResponseCode.UNAUTHORIZED, null);
                    return;
                }
                if (response.code() == ResponseCode.FORBIDDEN) {
                    callback.onSuccess(ResponseCode.FORBIDDEN, null);
                    return;
                }
                if (response.code() == ResponseCode.NOT_FOUND) {
                    callback.onSuccess(ResponseCode.NOT_FOUND, null);
                    return;
                }
                callback.onSuccess(ResponseCode.OK, response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

    public void  createAccount(String userToken, String connectId, int budget,int month){
    }


}