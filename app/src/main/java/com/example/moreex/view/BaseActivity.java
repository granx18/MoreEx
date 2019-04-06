package com.example.moreex.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moreex.R;
import com.example.moreex.presenter.ActivityCollector;


public class BaseActivity extends AppCompatActivity implements BaseView {

    public ProgressBar progressBar;

    public BaseActivity() {
        super();
        ActivityCollector.addActivity(this);
    }

    public void showProgressBar(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar = findViewById(R.id.progress_bar);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    public void hideProgressBar(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar = findViewById(R.id.progress_bar);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void makeToast(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showProgressBar();
            }
        });
    }

    @Override
    public void hideLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideProgressBar();

            }
        });
    }

    @Override
    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                makeToast(msg);
            }
        });
    }

    @Override
    public Activity getSelfActivity() {
        return this;
    }
}
