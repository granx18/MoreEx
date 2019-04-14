package com.example.moreex.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.api.StudentApi;
import io.swagger.client.model.ClassInfo;
import io.swagger.client.model.SportPlanInfo;
import io.swagger.client.model.SportTypeInfo;
import io.swagger.client.model.StudentInfo;

public class BaseVariable {
    public static String  sessionid;
    public static String password;
    public static StudentApi studentApi=new StudentApi();
    public static SportTypeInfo sportTypeInfo=new SportTypeInfo();
    public static StudentInfo studentInfo=new StudentInfo();
    public static List<ClassInfo> classInfo=new ArrayList<ClassInfo>();
    public static List<SportPlanInfo> sportPlanInfoList =new ArrayList<SportPlanInfo>();
}
