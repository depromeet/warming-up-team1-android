package com.depromeet.android.login.presenter;

import android.util.Log;

import com.depromeet.android.login.model.PopupCallback;
import com.depromeet.android.login.model.PopupRetrofitModel;
import com.depromeet.android.retrofit.ResponseCode;
import com.depromeet.android.util.PreferenceUtils;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class PopupPresenter
        implements PopupContract.Presenter, PopupCallback.RetrofitCallback {
    private PopupContract.View view;
    private PopupRetrofitModel retrofitModel;

    public PopupPresenter() {
        retrofitModel = new PopupRetrofitModel();
        retrofitModel.setCallback(this);
    }

    @Override
    public void onSuccess(int code, String responseConnectKey) {
        if (code == ResponseCode.UNAUTHORIZED) {
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
            String connectKey = responseConnectKey;
            Log.d("popup:connectkey", connectKey);          //확인용

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
    public void attachView(PopupContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void shareLink() {
        Log.d("PopupActivity!!", "sharelink1");

        String jwtToken = PreferenceUtils.getJwtToken();
        int mid = PreferenceUtils.getMid();
        retrofitModel.getConnectKey(jwtToken, mid);
        ResponseCallback<KakaoLinkResponse> callback = new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.d("PopupActivity!!", errorResult.getErrorMessage());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {
                Log.d("PopupActivity!!", "Successfully sent KakaoLink v2 message.");
            }
        };
        retrofitModel.setCallback(new PopupCallback.RetrofitCallback() {
            @Override
            public void onSuccess(int code, String responseConnectKey) {
                String checkKey = responseConnectKey;

                Map<String, String> templateArgs = new HashMap<>();
                templateArgs.put("description", "배우자와 함께 가계부를 공유해보세요!");
                templateArgs.put("check", checkKey); // key값 넣기
                KakaoLinkService.getInstance().sendCustom(view.getAppContext(), PreferenceUtils.getTemplateId(), templateArgs, null, callback);

            }

            @Override
            public void onFailure() {

            }
        });
    }

}
