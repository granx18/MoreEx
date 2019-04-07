package com.example.moreex.view.main;

import android.widget.BaseAdapter;

import com.example.moreex.view.BaseView;

public interface IFragment2 <T extends BaseAdapter>extends BaseView {
    void onSuccess(T adapter);
}
