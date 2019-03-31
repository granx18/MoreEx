package com.example.moreex.view.login;

import android.os.Bundle;
import android.transition.Explode;

import com.example.moreex.R;
import com.example.moreex.view.BaseActivity;

public class AActivityThree extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_three);

        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
    }
}
