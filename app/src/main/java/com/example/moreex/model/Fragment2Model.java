package com.example.moreex.model;

import android.os.AsyncTask;

import com.example.moreex.presenter.BaseCallback;
import com.example.moreex.presenter.Fragment2Callback;

import java.util.Comparator;
import java.util.List;

import io.swagger.client.model.ClassInfo;
import io.swagger.client.model.StudentInfo;

public class Fragment2Model <T extends BaseCallback>extends BaseModel {
    private T mCallback;

    public Fragment2Model(T mCallback){
        this.mCallback = mCallback;
    }

    public void getAdapterData(){
        new TestTask().execute();
    }

    private class TestTask extends AsyncTask<String,Integer,String[]> {

        @Override
        protected String[] doInBackground(String... strings) {
            try{
                List<ClassInfo> result = BaseVariable.
                        studentApi.getClassesInfo(BaseVariable.sessionid);
                if(result!=null)
                {
                   List<StudentInfo> studentInfosList=result.get(0).getStudents();
                   studentInfosList.sort(new comparator());
                   Integer size=studentInfosList.size();
                   String []s=new String[size];
                   for(int i=0;i<size;i++)
                   s[i]=studentInfosList.get(i).getName();
                    return s;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] s) {
            super.onPostExecute(s);
            ((Fragment2Callback)mCallback).onSuccess(s);
        }
    }

    private class comparator implements Comparator<StudentInfo> {

        @Override
        public int compare(StudentInfo o1, StudentInfo o2) {
            return o2.getSportDays()-o1.getSportDays();
        }
    }
}
