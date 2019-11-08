package com.depromeet.android.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseConnectMem {

    @SerializedName("connectKey")
    @Expose
    private String connectKey;

    @SerializedName("kakaoId")
    @Expose
    private String kakaoId;

    @SerializedName("mid")
    @Expose
    private int mid;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("profileImgUrl")
    @Expose
    private String profileImgUrl;

    public String getConnectKey() {
        return connectKey;
    }

    public void setConnectKey(String connectKey) {
        this.connectKey = connectKey;
    }

    public String getKakaoId() {
        return kakaoId;
    }

    public void setKakaoId(String kakaoId) {
        this.kakaoId = kakaoId;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }


}