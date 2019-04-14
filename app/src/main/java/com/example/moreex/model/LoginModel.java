package com.example.moreex.model;

import android.os.AsyncTask;

import com.example.moreex.presenter.BaseCallback;
import com.example.moreex.presenter.LoginCallback;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import io.swagger.client.ApiException;
import io.swagger.client.model.ClassInfo;
import io.swagger.client.model.Notice;
import io.swagger.client.model.SportPlanInfo;
import io.swagger.client.model.SportTypeInfo;
import io.swagger.client.model.StudentInfo;

public class LoginModel <T extends BaseCallback>extends BaseModel {
    private T mCallback;
    private String cardId;
    private String password;

    public LoginModel(T mCallBack)
    {
        this.mCallback = mCallBack;
    }

    public void logining(String cardId,String password){
        this.cardId=cardId;
        this.password=password;
        new LoginTask().execute("test");
    }

    /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public String EncoderByMd5(String str) {
        str=str+"!@#$PAOCAO$#@!";
        //确定计算方法
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //加密后的字符串

            String hashedPassword = Base64.getEncoder().encodeToString(md5.digest(str.getBytes("utf-8")));
            return hashedPassword;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
       return null;
    }
    private class LoginTask extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String...strings) {
            try{
                String hashedPassword=EncoderByMd5(password);
                try {
                    BaseVariable.sessionid = BaseVariable.studentApi.login(cardId, hashedPassword);
                    if(BaseVariable.sessionid!=null)
                    {
                        BaseVariable.password=password;
                    }
                    return  BaseVariable.sessionid;
                } catch (ApiException e) {
                    System.err.println("Exception when calling StudentApi#login");
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(BaseVariable.sessionid != null){
                ((LoginCallback)mCallback).onSuccess();
                ((LoginCallback)mCallback).onComplete();
                //登陆成功，查询基本信息表并保存
                new executeRequteBaseStudentInfoTask().execute();
            }
            else
                ((LoginCallback)mCallback).onFailure();
        }
    }

    private class executeRequteBaseStudentInfoTask
            extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            try {
                List<SportPlanInfo> sportPlanInfoResult = BaseVariable.studentApi.
                        getSportPlansInfo(BaseVariable.sessionid);
                if(sportPlanInfoResult!=null)
                    BaseVariable.sportPlanInfoList=sportPlanInfoResult;

                List<ClassInfo> ClassInfoResult = BaseVariable.
                        studentApi.getClassesInfo(BaseVariable.sessionid);
                if(ClassInfoResult!=null)
                    BaseVariable.classInfo=ClassInfoResult;

                StudentInfo StudentInfoResult = BaseVariable.studentApi.
                        getStudentInfo(BaseVariable.sessionid);
                if(StudentInfoResult!=null)
                    BaseVariable.studentInfo=StudentInfoResult;

                SportTypeInfo SportTypeInfoResult =
                        BaseVariable.studentApi.getSportTypesInfo(BaseVariable.sessionid);
                if(SportTypeInfoResult!=null)
                    BaseVariable.sportTypeInfo=SportTypeInfoResult;

            } catch (ApiException e) {
                System.err.println("Exception when calling StudentApi#get student base information");
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s)
        {

        }
    }

    public void executeRequestChangeIP(String ip,String port)
    {
        BaseVariable.studentApi.setBasePath("http://"+ip+":"+port);
        ((LoginCallback)mCallback).onSuccessChangeIP();
    }

    public void executeRequestNotice()
    {
        new requestNoticeTask().execute();
    }

    private class requestNoticeTask extends AsyncTask<String,Integer,List<Notice>>
    {
        @Override
        protected List<Notice> doInBackground(String... strings) {
            try {
                List<Notice> result = BaseVariable.
                        studentApi.getNotices(BaseVariable.sessionid);
                return result;
            } catch (ApiException e) {
                System.err.println("Exception when calling StudentApi#getNotices");
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Notice> list)
        {
            super.onPostExecute(list);
            if(list!=null)
            {
                ((LoginCallback)mCallback).onSuccessNotice(list);
            }
        }
    }
}
