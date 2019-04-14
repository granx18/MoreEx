package com.example.moreex.model;

import android.os.AsyncTask;

import com.example.moreex.presenter.BaseCallback;
import com.example.moreex.presenter.MeCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import io.swagger.client.ApiException;
import io.swagger.client.model.SportTypeInfo;
import io.swagger.client.model.StudentInfo;

public class MeModel <T extends BaseCallback> extends BaseModel {
    private T mCallback;

    public MeModel(T mCallback){
        this.mCallback = mCallback;
    }

    public void executeRequestStuInfo()
    {
        new requestStuInfoTask().execute();
    }

    private class requestStuInfoTask extends AsyncTask<String,Integer,StudentInfo>{

        @Override
        protected StudentInfo doInBackground(String... strings) {
            try {
                StudentInfo result = BaseVariable.
                        studentApi.getStudentInfo(BaseVariable.sessionid);
                if(result!=null)
                    BaseVariable.studentInfo=result;
            } catch (ApiException e) {
                System.err.println("Exception when calling StudentApi#getStudentInfo");
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
        protected void onPostExecute(StudentInfo studentInfo)
        {
            super.onPostExecute(studentInfo);
            if(studentInfo!=null)
                ((MeCallback)mCallback).onSuccessGetStuInfo(studentInfo);
        }
    }

    public void executeRequestSportTypeInfo(){
        new requestSportTypeInfoTask().execute();
    }

    private class requestSportTypeInfoTask extends
            AsyncTask<String,Integer, SportTypeInfo>{

        @Override
        protected SportTypeInfo doInBackground(String... strings) {
            try {
                SportTypeInfo result = BaseVariable.studentApi.
                        getSportTypesInfo(BaseVariable.sessionid);
                if(result!=null)
                    BaseVariable.sportTypeInfo=result;
                return result;
            } catch (ApiException e) {
                System.err.println("Exception when " +
                        "calling StudentApi#getSportTypesInfo");
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
        protected void onPostExecute(SportTypeInfo sportTypeInfo)
        {
            super.onPostExecute(sportTypeInfo);
            if(sportTypeInfo!=null)
                ((MeCallback)mCallback).onSuccessGetSportTypeInfo(sportTypeInfo);
        }
    }


}
