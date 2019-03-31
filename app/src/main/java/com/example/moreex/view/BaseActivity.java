package com.example.moreex.view;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moreex.R;


public class BaseActivity extends AppCompatActivity {

    public ProgressBar progressBar;

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
}
