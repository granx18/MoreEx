package com.example.moreex.model;

import android.os.AsyncTask;

import com.example.moreex.presenter.BaseCallback;

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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mCallback.onSuccess("test");
            mCallback.onComplete();
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            return "test";
        }
    }
}
