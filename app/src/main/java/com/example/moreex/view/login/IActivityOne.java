package com.example.moreex.view.login;

import com.example.moreex.view.BaseView;

import java.util.List;

import io.swagger.client.model.Notice;

public interface IActivityOne extends BaseView {
    void onSuccess();

    void showRightToast();

    void showWrongToast();

    void onSuccessNotice(List<Notice> list);
}
