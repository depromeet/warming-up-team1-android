package com.depromeet.android.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    private static final String KEY_KAKAO_TOKEN = "kakaoToken";
    private static final String KEY_SERVER_JWT_TOKEN = "serverJwtToken";
    private static final String KEY_MID = "mid";
    private static Context mAppContext;
    private static final String templateId = "18924";

    // Prevent instantiation
    private PreferenceUtils() {
    }

    public static void init(Context appContext) {
        mAppContext = appContext;
    }

    private static SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences("sendbird", Context.MODE_PRIVATE);
    }

    public static void setKakaoToken(String kakaoToken) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(KEY_KAKAO_TOKEN, kakaoToken).apply();
    }

    public static String getKakaoToken() {
        return getSharedPreferences().getString(KEY_KAKAO_TOKEN, "");
    }

    public static void setJwtToken(String jwtToken) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(KEY_SERVER_JWT_TOKEN, jwtToken).apply();
    }

    public static String getJwtToken() {
        return getSharedPreferences().getString(KEY_SERVER_JWT_TOKEN, "");
    }

    public static void setMid(int mid) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(KEY_MID, mid).apply();
    }

    public static int getMid() {
        return getSharedPreferences().getInt(KEY_MID, 0);
    }

    public static String getTemplateId() {
        return templateId;
    }
}
