package com.example.moreex.view.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.transition.Explode;

import com.example.moreex.R;
import com.example.moreex.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;

public class MainActivity extends BaseActivity {

    SpaceTabLayout tabLayout;
    List<Fragment> fragmentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        setAnime();

        initSpaceTabLayout(savedInstanceState);
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

        fragmentList.add(new Fragment());
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
}
