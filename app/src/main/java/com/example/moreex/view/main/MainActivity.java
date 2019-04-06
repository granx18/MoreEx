package com.example.moreex.view.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.transition.Explode;

import com.example.moreex.R;
import com.example.moreex.view.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    SpaceTabLayout tabLayout;
    List<Fragment> fragmentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        setAnime();

        initSpaceTabLayout(savedInstanceState);

        requestPermissions();
    }

    private void setAnime(){
        //animation    turn to this activity
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
    }

    void initSpaceTabLayout(Bundle savedInstanceState){
        //add the activity to display in a list
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment());

        ViewPager viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.spaceTabLayout);
        tabLayout.initialize(viewPager,getSupportFragmentManager(),fragmentList,savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        tabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    private void requestPermissions(){
        RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
        rxPermissions.request("android.permission.INTERNET",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.ACCESS_NETWORK_STATE",
                "android.permission.ACCESS_WIFI_STATE",
                "android.permission.READ_PHONE_STATE",
                "android.permission.ACCESS_COARSE_LOCATION").subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if(aBoolean);
                else{
                    finish();
                }
            }
        });
    }
}
