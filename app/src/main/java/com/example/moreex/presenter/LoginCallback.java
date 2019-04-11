package com.example.moreex.presenter;

public interface LoginCallback extends BaseCallback {
    void onSuccess(String data);

    void onFailure(String msg);

    void onComplete();
}
