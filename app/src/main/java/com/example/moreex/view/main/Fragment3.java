package com.example.moreex.view.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.moreex.R;
import com.example.moreex.view.BaseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment implements IFragment3{


    ListView listView;

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment3, container, false);
        listView = view.findViewById(R.id.listview_options);

        String[] adapterData = new String[]{"我","更改个人封面","查询个人跑操信息","更改密码","关于","感谢"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getSelfActivity()
                 ,android.R.layout.simple_list_item_1,adapterData);
        listView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
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
