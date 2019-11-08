package com.depromeet.android.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.depromeet.android.R;
import com.depromeet.android.login.presenter.PopupContract;
import com.depromeet.android.login.presenter.PopupPresenter;
import com.depromeet.android.main.view.MainActivity;


public class PopupActivity extends Activity
        implements PopupContract.View {
    private static final String TAG = "popupactivity";
    private PopupPresenter presenter;

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

        presenter = new PopupPresenter();
        presenter.attachView(this);

        createBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainActivity);
            }
        });

        createLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.shareLink();

            }
        });
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

    @Override
    public void toast(String msg) {
        Runnable r = () -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        this.runOnUiThread(r);
    }

    @Override
    public void startMainActivity(int code) {

    }

    @Override
    public void onUnauthorizedError() {
        toast("PopupActivity/server connect response : Unauthorized Error");
    }

    @Override
    public void onForbiddenError() {
        toast("PopupActivity/server connect response : Forbidden Error");
    }

    @Override
    public void onNotFoundError() {
        toast("PopupActivity/server connect response : Not Found Error");
    }

    @Override
    public void connectFail() {
        toast("PopupActivity/server connect response : Connect Fail");
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }


}

