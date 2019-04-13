package com.example.moreex.model;

import android.os.AsyncTask;
import android.util.Base64;

import com.example.moreex.presenter.BaseCallback;
import com.example.moreex.presenter.LoginCallback;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.swagger.client.ApiException;
import io.swagger.client.api.StudentApi;

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
        new TestTask().execute("test");
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
            String hashedPassword = Base64.getEncoder.encodeToString(md5.digest(str.getBytes("utf-8")));
            return hashedPassword;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
       return null;
    }
    private class TestTask extends AsyncTask<String,Integer,String>{


        @Override
        protected String doInBackground(String...strings) {
            try{
                String hashedPassword=EncoderByMd5(password);
                try {
                    BaseVariable.sessionid = BaseVariable.studentApi.login(cardId, hashedPassword);
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
            ((LoginCallback)mCallback).onSuccess("test");
            ((LoginCallback)mCallback).onComplete();
        }
    }
}
