package com.example.moreex.view;

public interface BaseView {

    /**
     * 显示正在加载进度
     */
    void showLoading();

    /**
     * 隐藏正在加载进度
     */
    void hideLoading();

    /**
     * 弹出Toast
     */
    void showToast(String msg);

    BaseActivity getSelfActivity();
}
