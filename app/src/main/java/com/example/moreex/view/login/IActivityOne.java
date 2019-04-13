package com.example.moreex.view.login;

import com.example.moreex.view.BaseView;

public interface IActivityOne extends BaseView {
    void onSuccess();

    void showRightToast();

    void showWrongToast();

    void onSuccessNotice(String title,String detail);
}
