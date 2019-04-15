package com.example.moreex.presenter;

import java.util.List;

import io.swagger.client.model.Notice;

public interface LoginCallback extends BaseCallback {
    void onSuccess();

    void onFailure();

    void onComplete();

    void onSuccessNotice(List<Notice> list);

    void onSuccessChangeIP();
}
