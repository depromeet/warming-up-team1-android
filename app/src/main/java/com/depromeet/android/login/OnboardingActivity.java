package com.depromeet.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.android.R;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        final ImageView moveImg = (ImageView) findViewById(R.id.moveImg);
        TextView nextBtn = (TextView) findViewById(R.id.nextBtn);
        final Intent loginActivity = new Intent(this, LoginActivity.class);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.onboarding_move);



        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                moveImg.startAnimation(animation);

            }
        };
        Timer timer = new Timer();
        timer.schedule(tt,3,3000);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(loginActivity);

            }
        });
    }
}
