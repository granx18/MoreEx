package com.example.moreex.presenter;

import java.util.List;

import io.swagger.client.model.TracePoint;

public interface Fragment1Callback extends BaseCallback {

    void onSuccessStartSport();
    void onFailureStartSport();

    void onSuccessSubmitTracePoint();
    void onFailureSubmitTracePoint();

    void onSuccessEndSport();
    void onFailureEndSport();

    void onSuccessSportTypeInfo();
    void onFailureSportTypeInfo();

    void onSuccessResumeMiles(double distance);
    void onSuccessResumeTime(long distance);
    void onSuccessResumeReDrawLine(List<TracePoint>list);
}
