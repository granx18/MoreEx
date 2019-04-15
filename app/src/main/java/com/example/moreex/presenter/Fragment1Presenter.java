package com.example.moreex.presenter;

import com.amap.api.maps.model.LatLng;
import com.example.moreex.model.Fragment1Model;
import com.example.moreex.view.BaseView;
import com.example.moreex.view.main.IFragment1;

public class Fragment1Presenter extends BasePresenter implements Fragment1Callback {
    private Fragment1Model fragment1Model;

    public Fragment1Presenter(BaseView view) {
        super(view);
        fragment1Model = new Fragment1Model(this);
}

    //向Model发起请求
    public void requestStartSport(){
        //todo
        fragment1Model.executeRequestStartSport();
    }

    public void requestSubmitTracePoint(LatLng latLng){
        //todo
        fragment1Model.executeRequestSubmitTracePoint(latLng);
    }

    public void requestEndSport(){
        //todo
        fragment1Model.executeRequestEndSport();
    }

    public void requestSportTypeInfo(){
        //todo
        fragment1Model.executeRerequestSportTypeInfoTask();
    }

    public void requestResumeMiles(){
        //todo
    }

    public void requestResumeTime(){
        //todo
    }

    //结合Model返回的数据进行操作
    @Override
    public void onSuccessStartSport() {
        //todo
        getView().showToast("start success");
        ((IFragment1)getView()).onSuccessStartSport();
    }

    @Override
    public void onSuccessSubmitTracePoint() {
        //todo
        getView().showToast("submit success");
    }

    @Override
    public void onSuccessEndSport() {
        //todo
        getView().showToast("end success");
        ((IFragment1)getView()).onSuccessEndSport();
    }

    @Override
    public void onSuccessSportTypeInfo() {
        //todo
        getView().showToast("get type success");
    }

    @Override
    public void onFailureStartSport() {
        //todo
        getView().showToast("start failed");
    }

    @Override
    public void onFailureSubmitTracePoint() {
        //todo
        getView().showToast("start failed");
    }

    @Override
    public void onFailureEndSport() {
        //todo
        getView().showToast("start failed");
    }

    @Override
    public void onFailureSportTypeInfo() {
        //todo
        getView().showToast("start failed");
    }

    @Override
    //distance m
    public void onSuccessResumeMiles(double distance) {
        onSuccessResumeMiles(distance);
    }

    @Override
    //distance millSeconds
    public void onSuccessResumeTime(long distance) {
        onSuccessResumeTime(distance);
    }
}
