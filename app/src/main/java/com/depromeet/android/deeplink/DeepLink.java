package com.depromeet.android.deeplink;


import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.depromeet.android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/*
공유 할 사람 인증할 때 사용 할 deep link
Deep Link example
- 현재 prameter로 key값 넘겨줌
- 만약 data를 담을 uri가 있다면 json으로 가능함 // 참고: https://nittaku.tistory.com/54?category=721201
 */
public class DeepLink extends AppCompatActivity {
    private static final String TAG = "FORBABY";
    private static final String SEGMENT_CHECK = "check";     //deep link uri에 있는 segment
    private static final String KEY_CODE = "key";            //deep link uri에 있는 key(인증 key)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);
        handleDeepLink();
        findViewById(R.id.btn_share_dynamic_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDynamicLink();
            }
        });
    }

    private void createDynamicLink() {
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(getCheckDeepLink())    //정보를 담는 json 사이트를 넣자!!
                .setDynamicLinkDomain("7depromeet.page.link")
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder(getPackageName()).build())
                .buildDynamicLink();
        Uri dylinkuri = dynamicLink.getUri();   //긴 URI
        Log.d(TAG, "long uri : " + dylinkuri.toString());
        //짧은 URI사용
        FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLongLink(dylinkuri)
                .buildShortDynamicLink()

                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();
                            Log.d(TAG, "short uri : " + shortLink.toString());    //짧은 URI
                        } else {
                            Log.w(TAG, task.toString());
                        }
                    }
                });
    }


    private void handleDeepLink() {
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        //app으로 실행 했을 경우 (deeplink 없는 경우)
                        if (pendingDynamicLinkData == null) {
                            Log.d(TAG, "No have dynamic link");
                            return;
                        }
                        //deeplink로 app 넘어 왔을 경우
                        Uri deepLink = pendingDynamicLinkData.getLink();
                        Log.d(TAG, "deepLink: " + deepLink);
                        String segment = deepLink.getLastPathSegment();

                        //uri에 있는 key값 가져오기
                        switch (segment) {
                            case SEGMENT_CHECK:
                                String code = deepLink.getQueryParameter(KEY_CODE);
                                showCheckDialog(code);      //임의로 dialog로 key값 띄움
                                break;
                        }

                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "getDynamicLink:onFailure", e);
                    }
                });
    }

    //확인용 다이얼로그
    private void showCheckDialog(String code) {
        new AlertDialog.Builder(this)
                .setMessage("Receive check key: " + code)
                .setPositiveButton("Confirm", null)
                .create().show();
    }

    //deeplink uri만들기
    private Uri getCheckDeepLink() {
        // 인증 할 key값 생성 후 넣기
        String keyCode = "AA123456";
        return Uri.parse("https://example.com/" + SEGMENT_CHECK + "?" + KEY_CODE + "=" + keyCode); //example

    }


}