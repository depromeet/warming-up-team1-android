package com.depromeet.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.depromeet.android.R;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Intent onBoardingActivity = new Intent(this, OnboardingActivity.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(onBoardingActivity);
            }
        }, 2000);
    }
}

