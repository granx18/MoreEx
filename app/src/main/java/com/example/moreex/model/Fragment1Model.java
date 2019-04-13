package com.example.moreex.model;

import android.os.AsyncTask;

import com.amap.api.maps.model.LatLng;
import com.example.moreex.presenter.BaseCallback;
import com.example.moreex.presenter.Fragment1Callback;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import io.swagger.client.ApiException;
import io.swagger.client.model.SportTypeInfo;
import io.swagger.client.model.TracePoint;

public class Fragment1Model<T extends BaseCallback> extends BaseModel {
    private T mCallback;

    public Fragment1Model(T mCallback) {
        this.mCallback = mCallback;
    }

    public void executeRequestStartSport()
    {
        startSportTask task=new startSportTask();
        task.execute();
    }

    public void executeRequestSubmitTracePoint(LatLng latLng){
        requestSubmitTracePointTask task=new requestSubmitTracePointTask();
        TracePoint point=new TracePoint();
        point.setLatitude(latLng.latitude);
        point.setLongitude(latLng.longitude);
        point.setTime(new Date(System.currentTimeMillis()));
        task.execute(point);
    }

    public void executeRequestEndSport()
    {
        endSportTask task=new endSportTask();
        task.execute();
    }

    public void executeRerequestSportTypeInfoTask()
    {
        requestSportTypeInfoTask task=new requestSportTypeInfoTask();
        task.execute();
    }
        private class startSportTask extends
                AsyncTask<String,Integer,Boolean>
        {
            @Override
            protected Boolean doInBackground(String...strings)
            {
                //？？？？？？？？？？？
                Integer planId = 56; // Integer ？？？？？？？？？？？？？
                try {
                    Boolean result = BaseVariable.studentApi.startSport
                            (BaseVariable.sessionid, planId);
                   return result;
                } catch (ApiException e) {
                    System.err.println("Exception when " +
                            "calling StudentApi#startSport");
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
            protected void onPostExecute(Boolean s) {
                super.onPostExecute(s);
                if(s==true)
                ((Fragment1Callback)mCallback).onSuccessStartSport();
                else
                    ((Fragment1Callback)mCallback).
                            onFailureSubmitTracePoint();
            }
        }

        private class requestSubmitTracePointTask extends
                AsyncTask<TracePoint,Integer,Boolean>
        {
            @Override
            protected Boolean doInBackground(TracePoint ...para) {
                TracePoint point = para[0]; // TracePoint |
                try {
                    Boolean result = BaseVariable.studentApi.
                            submitTracePoint(BaseVariable.sessionid, point);
                    System.out.println(result);
                } catch (ApiException e) {
                    System.err.println("Exception when calling " +
                            "StudentApi#submitTracePoint");
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
            protected void onPostExecute(Boolean r)
            {

                if(r==true)
                    ((Fragment1Callback)mCallback).
                            onFailureSubmitTracePoint();
                else
                    ((Fragment1Callback)mCallback).
                            onFailureSubmitTracePoint();
            }
        }

        private class endSportTask extends AsyncTask
                <String,Integer,Boolean>{
            @Override
            protected Boolean doInBackground(String... strings) {
                try {
                    Boolean result = BaseVariable.studentApi.
                            endSport(BaseVariable.sessionid);
                    System.out.println(result);
                } catch (ApiException e) {
                    System.err.println("Exception when " +
                            "calling StudentApi#endSport");
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
            protected void onPostExecute(Boolean r)
            {
                super.onPostExecute(r);
                if(r==true)
                ((Fragment1Callback)mCallback).onSuccessEndSport();
                else
                    ((Fragment1Callback)mCallback).
                            onFailureSubmitTracePoint();
            }

        }

        private class requestSportTypeInfoTask extends AsyncTask
                <String,Integer, SportTypeInfo>{
            @Override
            protected SportTypeInfo doInBackground(String... strings) {
                try {
                    SportTypeInfo result = BaseVariable.
                            studentApi.getSportTypesInfo
                            (BaseVariable.sessionid);
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
            protected void onPostExecute
                    (SportTypeInfo list){
                super.onPostExecute(list);
                if(list==null)
                    ((Fragment1Callback)mCallback).
                            onFailureSportTypeInfo();
                else
                    ((Fragment1Callback)mCallback).onSuccessSportTypeInfo();
            }
        }






}
