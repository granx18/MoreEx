package com.example.moreex.view.login;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moreex.R;
import com.example.moreex.presenter.LoginPresenter;
import com.example.moreex.view.BaseActivity;
import com.example.moreex.view.main.MainActivity;

import java.util.List;

import io.swagger.client.model.Notice;

public class AActivityOne extends BaseActivity implements IActivityOne{

    private EditText etUsername;
    private EditText etPassword;
    private Button btGo;
    private CardView cv;
    private FloatingActionButton fab;
    private TextView textView;

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
        textView = findViewById(R.id.forgot_pwd_textView);
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
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("请联系学校修改密码");
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

    //map通知栏
    private static final String NOTIFICATION_CHANNEL_NAME = "Notice";
    private NotificationManager notificationManager = null;
    boolean isCreateChannel = false;
    public void showNotice(String title,String detail){
        Notification.Builder builder = null;
        Notification notification = null;
        if(android.os.Build.VERSION.SDK_INT >= 26) {
            if (null == notificationManager) {
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            }
            String channelId = getPackageName();
            if(!isCreateChannel) {
                NotificationChannel notificationChannel = new NotificationChannel(channelId,
                        NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.enableLights(true);//是否在桌面icon右上角展示小圆点
                notificationChannel.setLightColor(Color.BLUE); //小圆点颜色
                notificationChannel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
                notificationManager.createNotificationChannel(notificationChannel);
                isCreateChannel = true;
            }
            builder = new Notification.Builder(getApplicationContext(), channelId);
        } else {
            builder = new Notification.Builder(getApplicationContext());
        }
        builder.setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(detail)
                .setWhen(System.currentTimeMillis());

        //回到MainActivity
        Intent intent = new Intent(AActivityOne.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), title.hashCode(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(contentIntent);
        //解决7.0通知合并问题
        builder.setGroupSummary(false);
        builder.setGroup("group");

        if (android.os.Build.VERSION.SDK_INT >= 16) {
            notification = builder.build();
        } else {
            notification=builder.getNotification();
        }
        notificationManager.notify(title.hashCode(),notification);


    }

    public void onSuccessNotice(List<Notice> list) {
        for(Notice i : list){
            showNotice(i.getTitle(),i.getDetail());
        }
    }

}
