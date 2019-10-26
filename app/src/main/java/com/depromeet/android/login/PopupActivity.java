package com.depromeet.android.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.depromeet.android.MainActivity;
import com.depromeet.android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import androidx.annotation.NonNull;


/*
공유 할 사람 인증할 때 사용 할 deep link
Deep Link example
- 현재 prameter로 key값 넘겨줌
- 만약 data를 담을 uri가 있다면 json으로 가능함 // 참고: https://nittaku.tistory.com/54?category=721201
 */
public class PopupActivity extends Activity {
    private static final String TAG = "popupactivity";
    private static final String SEGMENT_CHECK = "check";     //deep link uri에 있는 segment
    private static final String KEY_CODE = "key";            //deep link uri에 있는 key(인증 key)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams= new WindowManager.LayoutParams();
        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount= 0.7f;getWindow().setAttributes(layoutParams);

        setContentView(R.layout.activity_popup);

        TextView createBook = (TextView)findViewById(R.id.createBook);
        TextView createLink = (TextView)findViewById(R.id.createLink);

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

    //deeplink uri만들기
    private Uri getCheckDeepLink() {
        // 인증 할 key값 생성 후 넣기
        String keyCode = "AA123456";
        return Uri.parse("https://example.com/" + SEGMENT_CHECK + "?" + KEY_CODE + "=" + keyCode); //example

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
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
