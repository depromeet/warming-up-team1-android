package com.depromeet.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.depromeet.android.R;
import com.depromeet.android.login.presenter.LoginContract;
import com.depromeet.android.login.presenter.LoginPresenter;
import com.depromeet.android.main.view.MainActivity;
import com.depromeet.android.util.BaseActivity;
import com.depromeet.android.util.PreferenceUtils;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private LinearLayout btn_custom_login;
    private LoginButton btn_kakao_login;
    final String TAG = "LoginActivity";
    private SessionCallback callback;
    private String getLinkKey;

    private LoginPresenter presenter;
    @BindView(R.id.icon)
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        btn_custom_login = (LinearLayout) findViewById(R.id.btn_custom_login);
        btn_kakao_login = (LoginButton) findViewById(R.id.btn_kakao_login);

        Glide.with(this).load(R.raw.gificon).into(icon);

        Intent intent = getIntent();
        getLinkKey = intent.getExtras().getString("check", "");      //인증 key값
        Log.d("linkkey!!",getLinkKey);
        kakaoData();
        btn_custom_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

              //onClickLogout();
               btn_kakao_login.performClick();
            }

        });

        presenter = new LoginPresenter();
        presenter.attachView(this);
    }

    /**
     * 카카오톡
     **/
    private void kakaoData() {
       /* findViewById(R.id.kakaoLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLogout();
            }
        });*/

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);

        /** 토큰 만료시 갱신을 시켜준다**/
        if (Session.getCurrentSession().isOpenable()) {
            Session.getCurrentSession().checkAndImplicitOpen();
        }

        /** PreferenceUtils **/
        PreferenceUtils.setKakaoToken(Session.getCurrentSession().getTokenInfo().getAccessToken());

        Log.e(TAG, "토큰: " + Session.getCurrentSession().getTokenInfo().getAccessToken());
        Log.e(TAG, "토큰 리프레쉬토큰 : " + Session.getCurrentSession().getTokenInfo().getRefreshToken());
        Log.e(TAG, "토큰 파이어데이트 : " + Session.getCurrentSession().getTokenInfo().getRemainingExpireTime());
    }

    @Override
    public void toast(String msg) {
        Runnable r = () -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        this.runOnUiThread(r);
    }

    @Override
    public void startMainActivity(int code) {
        if (getLinkKey.length() == 0) {
            Intent popupActivity = new Intent(LoginActivity.this, PopupActivity.class);
            startActivity(popupActivity);
        } else {
            //connect member하기!!
            presenter.connect(getLinkKey);
            Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
            mainActivity.putExtra("check",getLinkKey);
            startActivity(mainActivity);
        }
    }

    @Override
    public void onUnauthorizedError() {
        toast("loginActivity/server connect response : Unauthorized Error");
    }

    @Override
    public void onForbiddenError() {
        toast("loginActivity/server connect response : Forbidden Error");
    }

    @Override
    public void onNotFoundError() {
        toast("loginActivity/server connect response : Not Found Error");
    }

    @Override
    public void connectFail() {
        toast("loginActivity/server connect response : Connect Fail");
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            Log.e(TAG, "카카오 로그인 성공 ");
            requestMe();

            presenter.login();

            return;
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Log.e(TAG, "exception : " + exception);
            }
        }
    }

    /**
     * 사용자에 대한 정보를 가져온다
     **/
    private void requestMe() {

        List<String> keys = new ArrayList<>();
        keys.add("properties.nickname");
        keys.add("properties.profile_image");
        keys.add("kakao_account.email");

        UserManagement.getInstance().me(keys, new MeV2ResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                super.onFailure(errorResult);
                Log.e(TAG, "requestMe onFailure message : " + errorResult.getErrorMessage());
            }

            @Override
            public void onFailureForUiThread(ErrorResult errorResult) {
                super.onFailureForUiThread(errorResult);
                Log.e(TAG, "requestMe onFailureForUiThread message : " + errorResult.getErrorMessage());
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e(TAG, "requestMe onSessionClosed message : " + errorResult.getErrorMessage());
            }

            @Override
            public void onSuccess(MeV2Response result) {
                Log.e(TAG, "requestMe onSuccess message : " + " ID: " + result.getId() + " Nickname: " + result.getNickname());
            }

        });
    }

    /**
     * 로그아웃시
     **/
    private void onClickLogout() {

        UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e(TAG, "카카오 로그아웃 onSessionClosed");
            }

            @Override
            public void onNotSignedUp() {
                Log.e(TAG, "카카오 로그아웃 onNotSignedUp");
            }

            @Override
            public void onSuccess(Long result) {
                Log.e(TAG, "카카오 로그아웃 onSuccess");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}