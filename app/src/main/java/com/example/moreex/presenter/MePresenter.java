package com.example.moreex.presenter;

import com.example.moreex.view.BaseView;
import com.example.moreex.view.main.Me;

import io.swagger.client.model.SportTypeInfo;
import io.swagger.client.model.StudentInfo;

public class MePresenter extends BasePresenter implements MeCallback {
    public MePresenter(BaseView view) {
        super(view);
    }

    public void requestStuInfo(){
        //todo
    }

    public void requestSportTypeInfo(){
        //todo
    }

    @Override
    public void onSuccessGetStuInfo(StudentInfo studentInfo) {
        ((Me)getView()).setStudentInfo(studentInfo);
    }

    @Override
    public void onSuccessGetSportTypeInfo(SportTypeInfo sportTypeInfo) {
        ((Me)getView()).setSportTypeInfo(sportTypeInfo);
    }
}
