package com.example.moreex.presenter;

import android.widget.ArrayAdapter;

import com.example.moreex.model.Fragment2Model;
import com.example.moreex.view.BaseView;
import com.example.moreex.view.main.Fragment2;

public class Fragment2Presenter extends BasePresenter implements Fragment2Callback{
    private Fragment2Model fragment2Model;
    public Fragment2Presenter(BaseView view) {
        super(view);

        fragment2Model = new Fragment2Model(this);
    }

    //向Model发起请求
    public void requestAdapter(){
        fragment2Model.getAdapterData();
    }


    @Override
    //结合Model返回的数据生成Adapter
    public void onSuccess(String[] adapterData) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
               getView().getSelfActivity() ,android.R.layout.simple_list_item_1,adapterData);
        ((Fragment2)getView()).onSuccess(adapter);
    }

    @Override
    public void onSuccess(String data) {

    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void onComplete() {

    }


}
