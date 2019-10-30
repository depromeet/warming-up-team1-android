package com.depromeet.android.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.depromeet.android.MainActivity;
import com.depromeet.android.R;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class PopupActivity extends Activity {
    private static final String TAG = "popupactivity";

    private ResponseCallback<KakaoLinkResponse> callback;
    private Map<String, String> serverCallbackArgs = null;
    private String checkKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.7f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.activity_popup);

        TextView createBook = (TextView) findViewById(R.id.createBook);
        TextView createLink = (TextView) findViewById(R.id.createLink);

        final Intent mainActivity = new Intent(this, MainActivity.class);


        createBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainActivity);
            }
        });

        createLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareLink();
            }
        });


        callback = new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.d(TAG, errorResult.getErrorMessage());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {
                Log.d(TAG, "Successfully sent KakaoLink v2 message.");
            }
        };
    }

    private void shareLink() {
        String templateId = "18924";

        Map<String, String> templateArgs = new HashMap<>();
        templateArgs.put("description", "티끌모아육아, 공유해요!");
        templateArgs.put("check", checkKey); // key값 넣기
        KakaoLinkService.getInstance().sendCustom(this, templateId, templateArgs, serverCallbackArgs, callback);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

}

