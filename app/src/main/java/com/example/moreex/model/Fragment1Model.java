package com.example.moreex.model;

import android.os.AsyncTask;

import com.amap.api.maps.model.LatLng;
import com.example.moreex.presenter.BaseCallback;
import com.example.moreex.presenter.Fragment1Callback;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import io.swagger.client.ApiException;
import io.swagger.client.model.SportRecordInfo;
import io.swagger.client.model.SportTypeInfo;
import io.swagger.client.model.StudentInfo;
import io.swagger.client.model.TracePoint;

public class Fragment1Model<T extends BaseCallback> extends BaseModel {
    private T mCallback;
    private Integer planId;

    public Fragment1Model(T mCallback) {
        this.mCallback = mCallback;
        planId=-1;
    }


    public void executeRequestStartSport()
    {
        new startSportTask().execute();
    }

    public void executeRequestSubmitTracePoint(LatLng latLng){
        requestSubmitTracePointTask task=new requestSubmitTracePointTask();
        TracePoint point=new TracePoint();
        point.setLatitude(latLng.latitude);
        point.setLongitude(latLng.longitude);
        point.setTime(System.currentTimeMillis());
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
                StudentInfo tempResult = null;
                try {
                    tempResult = BaseVariable.
                            studentApi.getStudentInfo(BaseVariable.sessionid);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                }
                if(tempResult!=null)
                    BaseVariable.studentInfo=tempResult;


                if (BaseVariable.studentInfo.getPresentSportRecordId() != -1) {
                    try {
                        List<TracePoint> result = BaseVariable.studentApi.getTrace(BaseVariable.sessionid, BaseVariable.studentInfo.getPresentSportRecordId());
                        if(result.size()<1) {
                            ((Fragment1Callback) mCallback).onSuccessResumeTime(0);
                            ((Fragment1Callback) mCallback).onSuccessResumeMiles(0);
                        }
                        else {
                            long time = result.get(result.size() - 1).getTime() - result.get(0).getTime();
                            double distance = 0;
                            for (int i = 1; i < result.size(); i++) {
                                distance += result.get(i).LengthFromM((result.get((i - 1))));
                            }
                            ((Fragment1Callback) mCallback).onSuccessResumeTime(time);
                            ((Fragment1Callback) mCallback).onSuccessResumeMiles(distance);
                            ((Fragment1Callback) mCallback).onSuccessResumeReDrawLine(result);
                        }
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                else {
                    long time = System.currentTimeMillis();
                    for (int i = 0; i < BaseVariable.sportPlanInfoList.size(); i++) {
                        if (time >= BaseVariable.sportPlanInfoList.get(i).getStartTime()
                                && time <= BaseVariable.sportPlanInfoList.get(i).getEndTime()
                                && BaseVariable.sportPlanInfoList.get(i).getCancelled() == false) {
                            planId = BaseVariable.sportPlanInfoList.get(i).getPlanId();
                        }
                    }
                    if (planId == -1) {
                        System.err.println("Exception when " +
                                "calling StudentApi#startSport");
                        return Boolean.FALSE;
                    }
                    try {
                        Boolean result = BaseVariable.studentApi.startSport
                                (BaseVariable.sessionid, planId).getResult();
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
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean s) {
                super.onPostExecute(s);
                if(s==true) {
                    ((Fragment1Callback) mCallback).onSuccessStartSport();
                }
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
                List<SportRecordInfo> SportRecordInfoResult=null;
                try {
                    SportRecordInfoResult = BaseVariable.studentApi.getSportRecords(BaseVariable.sessionid);

                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                }

                try {
                    int i;
                    for (i = 0; i < SportRecordInfoResult.size() && SportRecordInfoResult.get(i).getRecordId() != BaseVariable.studentInfo.getPresentSportRecordId(); i++)
                        ;

                    int j = 0;
                    for (; j < BaseVariable.sportPlanInfoList.size() && BaseVariable.sportPlanInfoList.get(j).getPlanId() != SportRecordInfoResult.get(i).getPlanId(); j++)
                        ;

                    if (point.getTime() >= BaseVariable.sportPlanInfoList.get(j).getEndTime())
                        executeRequestEndSport();
                    else {
                        try {
                            Boolean result = BaseVariable.studentApi.
                                    submitTracePoint(BaseVariable.sessionid, point).getResult();
                            return result;
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

                    }
                }
                catch (NullPointerException e)
                {
                    System.err.println("NullPointerException when calling " +
                            "StudentApi#submitTracePoint");
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean r)
            {

                if(r)
                    ((Fragment1Callback)mCallback).onSuccessSubmitTracePoint();
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
                            endSport(BaseVariable.sessionid).getResult();
                    return result;
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
                return false;
            }

            @Override
            protected void onPostExecute(Boolean r)
            {
                super.onPostExecute(r);
                if(r)
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
                    (SportTypeInfo sportType){
                super.onPostExecute(sportType);
                if(sportType==null)
                    ((Fragment1Callback)mCallback).
                            onFailureSportTypeInfo();
                else {
                    BaseVariable.sportTypeInfo=sportType;
                    ((Fragment1Callback) mCallback).onSuccessSportTypeInfo();
                }
            }
        }






}
