package com.example.moreex.model;

import com.example.moreex.presenter.BaseCallback;

public class Fragment1Model<T extends BaseCallback> extends BaseModel {
    private T mCallback;

    public Fragment1Model(T mCallback){
        this.mCallback = mCallback;
    }


}
