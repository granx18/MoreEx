package com.example.moreex.view.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.transition.Explode;
import android.widget.Toast;

import com.example.moreex.R;
import com.example.moreex.model.ActivityCollector;
import com.example.moreex.view.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
        fragmentList.add(new Fragment3());

        ScrollViewPager viewPager = findViewById(R.id.viewPager);
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
                "android.permission.ACCESS_COARSE_LOCATION",
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.CHANGE_WIFI_STATE",
                "android.permission.INTERNET",
                "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",
                "android.permission.BLUETOOTH",
                "android.permission.BLUETOOTH_ADMIN").subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if(aBoolean);
                else{
                    showToast("权限不足，请重试");
                    ActivityCollector.finishAll();
                }
            }
        });
    }

    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    @Override
    public void onBackPressed() {
        if(!mBackKeyPressed){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
             mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
              @Override
              public void run() {
                  mBackKeyPressed = false;
            }
        }, 2000);
    }
        else{//退出程序
            ActivityCollector.finishAll();
        }
    }








}
