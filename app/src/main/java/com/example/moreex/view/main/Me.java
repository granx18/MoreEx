package com.example.moreex.view.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.moreex.R;
import com.example.moreex.presenter.MePresenter;
import com.example.moreex.view.BaseActivity;

import io.swagger.client.model.SportTypeInfo;
import io.swagger.client.model.StudentInfo;

public class Me extends BaseActivity {

    public ListView listView;
    public StudentInfo studentInfo;
    public SportTypeInfo sportTypeInfo;

    public MePresenter mePresenter = new MePresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me);

        listView = findViewById(R.id.me_listView);

        String[] adapterData = new String[]{"学生名字","学号","运动类型id","已跑天数","最少运动天数","每次最少长度"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getSelfActivity()
                ,android.R.layout.simple_list_item_1,adapterData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new MyOnItemClickListener());

        mePresenter.requestSportTypeInfo();
        mePresenter.requestStuInfo();
    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0: {
                    showToast(studentInfo.getName());
                    break;
                }
                case 1: {
                    showToast(studentInfo.getStudentId());
                    break;
                }
                case 2: {
                    showToast(String.valueOf(studentInfo.getSportTypeId()));
                    break;
                }
                case 3:{
                    showToast(String.valueOf(studentInfo.getSportDays()));
                    break;
                }
                case 4:{
                    showToast(String.valueOf(sportTypeInfo.getLeastSportDays()));
                    break;
                }
                case 5:{
                    showToast(String.valueOf(sportTypeInfo.getLeastLengthKMOnceSport()));
                    break;
                }
            }
        }
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public void setSportTypeInfo(SportTypeInfo sportTypeInfo) {
        this.sportTypeInfo = sportTypeInfo;
    }
}
