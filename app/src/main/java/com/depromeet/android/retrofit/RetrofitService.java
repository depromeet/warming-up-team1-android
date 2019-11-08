package com.depromeet.android.retrofit;

import com.depromeet.android.data.ResponseAuth;
import com.depromeet.android.data.ResponseConnectMem;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitService {

    @POST("/api/members/login")
    Call<ResponseAuth> login(
        @Body JsonObject kakaoToken
    );

    @GET("/api/members/connect-key/{mid}")
    Call<String> getConnectKey(@Header("authorization") String Authorization,
                                          @Path("mid") int mid);

    @PUT("/api/members/connect/{mid}/{key}")
    Call<ResponseConnectMem> connectMem(@Header("authorization") String Authorization
                                         ,@Path("mid")int mid,@Path("key") String connectKey);

}
