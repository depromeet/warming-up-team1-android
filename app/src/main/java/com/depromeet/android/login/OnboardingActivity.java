package com.depromeet.android.login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.android.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        final Intent splashIntent = getIntent();
        String getLinkKey = splashIntent.getExtras().getString("check","");       //인증 key값

        final Intent loginActivity = new Intent(this, LoginActivity.class);
        loginActivity.putExtra("check",getLinkKey);

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.onboarding_move);

        getHashKey();

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
                finish();
            }
        });
    }

    //릴리즈용으로 추후에 변경해야 함
    private void getHashKey() {
        try {                                                        // 패키지이름을 입력해줍니다.
            PackageInfo info = getPackageManager().getPackageInfo("com.depromeet.android", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KEY_HASH", "key_hash=" + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
