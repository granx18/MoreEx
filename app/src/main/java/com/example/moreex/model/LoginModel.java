package com.example.moreex.model;

import android.os.AsyncTask;

import com.example.moreex.presenter.BaseCallback;
import com.example.moreex.presenter.LoginCallback;

public class LoginModel <T extends BaseCallback>extends BaseModel {
    private T mCallback;
    public LoginModel(T mCallBack){
        this.mCallback = mCallBack;
    }

    public void logining(){
        new TestTask().execute("test");
    }

    private class TestTask extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            return "test";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ((LoginCallback)mCallback).onSuccess("test");
            ((LoginCallback)mCallback).onComplete();
        }
    }
}
