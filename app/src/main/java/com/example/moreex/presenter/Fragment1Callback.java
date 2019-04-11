package com.example.moreex.presenter;

public interface Fragment1Callback extends BaseCallback {

    void onSuccessStartSport();
    void onFailureStartSport();

    void onSuccessSubmitTracePoint();
    void onFailureSubmitTracePoint();

    void onSuccessEndSport();
    void onFailureEndSport();

    void onSuccessSportTypeInfo();
    void onFailureSportTypeInfo();
}
