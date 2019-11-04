package com.depromeet.android.login.presenter;

import com.depromeet.android.data.ResponseAuth;
import com.depromeet.android.login.model.LoginCallback;
import com.depromeet.android.login.model.LoginRetrofitModel;
import com.depromeet.android.retrofit.ResponseCode;
import com.depromeet.android.util.PreferenceUtils;

public class LoginPresenter
        implements LoginContract.Presenter, LoginCallback.RetrofitCallback {

    private LoginContract.View view;
    private LoginRetrofitModel retrofitModel;

    public LoginPresenter() {
        retrofitModel = new LoginRetrofitModel();
        retrofitModel.setCallback(this);
    }

    @Override
    public void login() {
        String kakaoToken = PreferenceUtils.getKakaoToken();
        retrofitModel.login(kakaoToken);
    }

    @Override
    public void onSuccess(int code, ResponseAuth responseAuth) {
        if(code == ResponseCode.UNAUTHORIZED) {
            view.onUnauthorizedError();
            return;
        }

        if (code == ResponseCode.FORBIDDEN) {
            view.onForbiddenError();
            return;
        }

        if (code == ResponseCode.NOT_FOUND) {
            view.onNotFoundError();
            return;
        }

        if (code == ResponseCode.OK) {
            PreferenceUtils.setJwtToken(responseAuth.getJwtToken());
            view.startMainActivity(code);
            return;
        }
    }

    @Override
    public void onFailure() {
        view.connectFail();
        return;
    }

    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
