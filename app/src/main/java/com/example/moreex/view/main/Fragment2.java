package com.example.moreex.view.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;

import com.ecloud.pulltozoomview.PullToZoomListViewEx;
import com.example.moreex.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {


    public Fragment2() {
        // Required empty public constructor
    }

    private PullToZoomListViewEx listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pull_to_zoom_list_view,container,false);

        listView = view.findViewById(R.id.listview);

        String[] adapterData = new String[]{"Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient",
                "DDMS", "Android Studio", "Fragment", "Loader", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),android.R.layout.simple_list_item_1,adapterData);
        listView.setAdapter(adapter);

        return view;
    }

}
