package com.example.moreex.model;

import com.example.moreex.presenter.BaseCallback;
import com.example.moreex.presenter.Fragment2Callback;

public class Fragment2Model <T extends BaseCallback>extends BaseModel {
    private T mCallback;

    public Fragment2Model(T mCallback){
        this.mCallback = mCallback;
    }

    public void getAdapterData(){
        String[] adapterData = new String[]{"Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient",
                "DDMS", "Android Studio", "Fragment", "Loader", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient"};
        ((Fragment2Callback)mCallback).onSuccess(adapterData);
    }

}
