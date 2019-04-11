package com.example.moreex.view.main;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.ecloud.pulltozoomview.PullToZoomListViewEx;
import com.example.moreex.R;
import com.example.moreex.presenter.Fragment2Presenter;
import com.example.moreex.view.BaseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment implements IFragment2{

    private Fragment2Presenter fragment2Presenter = new Fragment2Presenter(this);

    public Fragment2() {
        // Required empty public constructor
    }

    private PullToZoomListViewEx listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pull_to_zoom_list_view,container,false);

        listView = view.findViewById(R.id.listview);

        //临时的adapter for listView
        String[] adapterData = new String[]{"1", "2", "3", "4", "5", "6", "7", "8",
                "9", "10", "11", "12", "13", "14", "15", "16",
                "17", "18", "19", "20"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getSelfActivity() ,android.R.layout.simple_list_item_1,adapterData);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            fragment2Presenter.requestAdapter();
        }
    }

    @Override
    public void onSuccess(BaseAdapter adapter) {
        listView.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        getSelfActivity().showLoading();
    }

    @Override
    public void hideLoading() {
        getSelfActivity().hideLoading();
    }

    @Override
    public void showToast(String msg) {
        getSelfActivity().showToast(msg);
    }

    @Override
    public BaseActivity getSelfActivity() {
        return (BaseActivity)this.getActivity();
    }
}
