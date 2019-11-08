package com.depromeet.android.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAuth {

    @SerializedName("jwtToken")
    @Expose
    private String jwtToken;

    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;

    @SerializedName("mid")
    @Expose
    private int mid;

    public int getMid() {
        return mid;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}