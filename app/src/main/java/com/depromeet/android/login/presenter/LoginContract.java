package com.depromeet.android.login.presenter;

public interface LoginContract {
    interface View {
        void toast(String msg);
        void startMainActivity(int code);
        void onUnauthorizedError();
        void onForbiddenError();
        void onNotFoundError();
        void connectFail();
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void login();
        void connect(String connectKey);
    }
}
