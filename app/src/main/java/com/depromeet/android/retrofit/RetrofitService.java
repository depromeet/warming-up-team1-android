package com.depromeet.android.retrofit;

import com.depromeet.android.data.ResponseAuth;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST("/api/members/login")
    Call<ResponseAuth> login(
        @Body JsonObject kakaoToken
    );

}
