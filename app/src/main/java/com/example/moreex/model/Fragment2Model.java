package com.example.moreex.model;

import android.os.AsyncTask;

import com.example.moreex.presenter.BaseCallback;
import com.example.moreex.presenter.Fragment2Callback;

public class Fragment2Model <T extends BaseCallback>extends BaseModel {
    private T mCallback;

    public Fragment2Model(T mCallback){
        this.mCallback = mCallback;
    }

    public void getAdapterData(){
        new TestTask().execute();
    }

    private class TestTask extends AsyncTask<String,Integer,String> {

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
            String[] adapterData = new String[]{"Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient",
                    "DDMS", "Android Studio", "Fragment", "Loader", "Activity", "Service", "Content Provider", "Intent",
                    "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient", "Activity", "Service", "Content Provider", "Intent",
                    "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient"};
            ((Fragment2Callback)mCallback).onSuccess(adapterData);
        }
    }
}
