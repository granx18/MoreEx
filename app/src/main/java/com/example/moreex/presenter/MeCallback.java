package com.example.moreex.presenter;

import io.swagger.client.api.StudentApi;
import io.swagger.client.model.SportTypeInfo;
import io.swagger.client.model.StudentInfo;

public interface MeCallback extends BaseCallback {
    void onSuccessGetStuInfo(StudentInfo studentInfo);

    void onSuccessGetSportTypeInfo(SportTypeInfo sportTypeInfo);
}
