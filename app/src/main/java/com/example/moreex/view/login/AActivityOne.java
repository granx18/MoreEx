package com.example.moreex.view.login;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.moreex.R;
import com.example.moreex.presenter.LoginPresenter;
import com.example.moreex.view.BaseActivity;
import com.example.moreex.view.main.MainActivity;

public class AActivityOne extends BaseActivity implements IActivityOne{

    private EditText etUsername;
    private EditText etPassword;
    private Button btGo;
    private CardView cv;
    private FloatingActionButton fab;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_one);
        initView();
        setListener();
        loginPresenter = new LoginPresenter(this);
    }

    private void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btGo = findViewById(R.id.bt_go);
        cv = findViewById(R.id.cv);
        fab = findViewById(R.id.fab);
    }

    private void setListener() {
        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showActivityThree();
                loginPresenter.requestLogin(etUsername.getText().toString(),etPassword.getText().toString());
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showActivityTwo();
            }
        });
    }


    @Override
    public void onSuccess() {
        ((AActivityOne)getSelfActivity()).showActivityThree();
    }

    @Override
    public void showRightToast() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast("Success");
            }
        });
    }

    @Override
    public void showWrongToast() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast("Username or password wrong");
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fab.hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fab.show();
    }

    @Override
    protected void onDestroy() {
        loginPresenter.detachView();
        super.onDestroy();
    }

    public void showActivityThree(){
        Explode explode = new Explode();
        explode.setDuration(500);

        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(AActivityOne.this);
        Intent i2 = new Intent(AActivityOne.this, MainActivity.class);
        startActivity(i2, oc2.toBundle());
    }

    public void showActivityTwo(){
        getWindow().setExitTransition(null);
        getWindow().setEnterTransition(null);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AActivityOne.this, fab, fab.getTransitionName());
        startActivity(new Intent(AActivityOne.this, AActivityTwo.class), options.toBundle());

    }

}
