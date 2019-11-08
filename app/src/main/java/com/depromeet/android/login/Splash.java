package com.depromeet.android.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.depromeet.android.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Splash extends AppCompatActivity {

    @BindView(R.id.icon)
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Glide.with(this).load(R.raw.gificon).into(icon);

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
        }, 3000);
    }
}

