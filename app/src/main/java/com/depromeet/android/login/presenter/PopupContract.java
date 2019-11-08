package com.depromeet.android.login.presenter;

import android.content.Context;

public interface PopupContract {
    interface View {
        void toast(String msg);
        void startMainActivity(int code);
        void onUnauthorizedError();
        void onForbiddenError();
        void onNotFoundError();
        void connectFail();
        Context getAppContext();
        Context getActivityContext();
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void shareLink();

    }
}
