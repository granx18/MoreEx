package com.example.moreex.presenter;

import com.example.moreex.model.MeModel;
import com.example.moreex.view.BaseView;
import com.example.moreex.view.main.Me;

import io.swagger.client.model.SportTypeInfo;
import io.swagger.client.model.StudentInfo;

public class MePresenter extends BasePresenter implements MeCallback {
    private MeModel meModel = new MeModel(this);
    public MePresenter(BaseView view) {
        super(view);
    }

    public void requestStuInfo(){
        //todo
        meModel.executeRequestStuInfo();
    }

    public void requestSportTypeInfo(){
        //todo
        meModel.executeRequestSportTypeInfo();
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
