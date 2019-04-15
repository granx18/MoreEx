package com.example.moreex.view.main;

import com.example.moreex.view.BaseView;

import java.util.List;

import io.swagger.client.model.TracePoint;

public interface IFragment1 extends BaseView {
    void onSuccessStartSport();

    void onSuccessEndSport();

    void onSuccessResumeMiles(double distance);

    void onSuccessResumeTime(long distance);

    void onSuccessResumeReDrawLine(List<TracePoint> list);
}
