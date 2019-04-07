package com.example.moreex.presenter;

import com.example.moreex.view.BaseView;

import java.lang.ref.WeakReference;

public class BasePresenter <T extends BaseView> {

    //弱引用，防内存泄漏问题
    private WeakReference<T> mViewRef;

    BasePresenter(T view){
        attachView(view);
    }

    public boolean isViewAttach(){
        return mViewRef != null && mViewRef.get() != null;
    }

    public void attachView(T view){
        mViewRef = new WeakReference<>(view);
    }

    public void detachView(){
        if(mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public T getView(){
        return mViewRef.get();
    }
}
