package com.depromeet.android.login.model;

import com.depromeet.android.data.ResponseAuth;
import com.depromeet.android.retrofit.ResponseCode;
import com.depromeet.android.retrofit.RetrofitService;
import com.depromeet.android.retrofit.RetrofitServiceManager;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRetrofitModel {
    private LoginCallback.RetrofitCallback callback;
    private RetrofitService retrofitService;

    public LoginRetrofitModel() {
        retrofitService = RetrofitServiceManager.getRetrofitInstance();
    }

    public void setCallback(LoginCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

    public void login(String userToken) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("", userToken);

        Call<ResponseAuth> call = retrofitService.login(jsonObject);
        call.enqueue(new Callback<ResponseAuth>() {
            @Override
            public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
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
                callback.onSuccess(ResponseCode.OK, response.body());
            }

            @Override
            public void onFailure(Call<ResponseAuth> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }
}
