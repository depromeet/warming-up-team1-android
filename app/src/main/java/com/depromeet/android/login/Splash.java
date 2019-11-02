package com.depromeet.android.login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.depromeet.android.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Intent onBoardingActivity = new Intent(this, OnboardingActivity.class);
        onBoardingActivity.putExtra("check","");            //인증 key값

        Intent getIntent = getIntent();

        Uri uri = getIntent.getData();
        if (uri != null) {
            Log.d("kakaoLink", uri.toString());
            String key = uri.getQueryParameter("check");

            if (key != null) {
                onBoardingActivity.putExtra("check",key);
                Log.d("kakaoLink", key);
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(onBoardingActivity);
            }
        }, 2000);
    }
}

