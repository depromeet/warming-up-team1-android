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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PopupActivity extends Activity
        implements PopupContract.View {

    @BindView(R.id.createBook)
    TextView createBook;
    @BindView(R.id.createLink)
    TextView createLink;

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
        ButterKnife.bind(this);

        presenter = new PopupPresenter();
        presenter.attachView(this);
    }

    @OnClick(R.id.createBook)
    public void onCreateBookClick() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }

    @OnClick(R.id.createLink)
    public void onCreateLinkClick() {
        presenter.shareLink();
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

