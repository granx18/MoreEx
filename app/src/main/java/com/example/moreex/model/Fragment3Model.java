package com.example.moreex.model;

import android.os.AsyncTask;

import com.example.moreex.presenter.BaseCallback;
import com.example.moreex.presenter.Fragment3Callback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import io.swagger.client.ApiException;

public class Fragment3Model <T extends BaseCallback>extends BaseModel {
    private T mCallback;

    public Fragment3Model(T mCallback)
    {
        this.mCallback=mCallback;
    }

    public void executeRequestChangePwd(String s){
        new requestChangePwdTask().execute(s);
    }

    private class requestChangePwdTask extends
            AsyncTask<String,Integer,Boolean>
    {

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                Boolean result = BaseVariable.studentApi.
                        changePassword(BaseVariable.sessionid, BaseVariable.password, strings[0]);
                if(result==true)
                    BaseVariable.password=strings[0];
                return result;
            } catch (ApiException e) {
                System.err.println("Exception when calling StudentApi#changePassword");
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean b)
        {
            super.onPostExecute(b);
            if(b==true)
                ((Fragment3Callback)mCallback).onSuccessChangePwd();
        }
    }


}
