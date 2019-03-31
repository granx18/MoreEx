package com.example.moreex.presenter;

public interface BaseCallback {
    void onSuccess(String data);

    void onFailure(String msg);

    void onComplete();
}
