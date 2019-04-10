package com.example.moreex.presenter;

import com.example.moreex.model.Fragment1Model;
import com.example.moreex.view.BaseView;

import io.swagger.client.model.TracePoint;

public class Fragment1Presenter extends BasePresenter implements Fragment1Callback {
    private Fragment1Model fragment1Model;

    public Fragment1Presenter(BaseView view) {
        super(view);

        fragment1Model = new Fragment1Model(this);
    }

    //向Model发起请求
    public void requestStartSport(String sessionId,Integer planId){
        //todo
    }

    public void requestSubmitTracePoint(String sessionId, TracePoint tracePoint){
        //todo
    }

    public void requestEndSport(String sessionId){
        //todo
    }

    public void requestSportTypeInfo(String sessionId){
        //todo
    }

    //结合Model返回的数据进行操作

    @Override
    public void onSuccessStartSport() {
        //todo
    }

    @Override
    public void onSuccessSubmitTracePoint() {
        //todo

    }

    @Override
    public void onSuccessEndSport() {
        //todo

    }

    @Override
    public void onSuccessSportTypeInfo() {
        //todo

    }

    @Override
    public void onFailureStartSport() {
        //todo

    }

    @Override
    public void onFailureSubmitTracePoint() {
        //todo

    }

    @Override
    public void onFailureEndSport() {
        //todo

    }

    @Override
    public void onFailureSportTypeInfo() {
        //todo

    }
}
