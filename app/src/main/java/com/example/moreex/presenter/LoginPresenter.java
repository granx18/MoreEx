package com.example.moreex.presenter;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;

import com.example.moreex.model.LoginModel;
import com.example.moreex.view.BaseView;
import com.example.moreex.view.login.IActivityOne;
import com.example.moreex.view.login.IActivityTwo;

import java.util.List;

import io.swagger.client.model.Notice;

import static android.content.Context.NOTIFICATION_SERVICE;

public class LoginPresenter extends BasePresenter implements LoginCallback{

    private LoginModel loginModel;
    //公告通知类
    NotificationManager mNotificationManager;
    NotificationCompat.Builder mBuilder;
    public LoginPresenter(BaseView view) {
        super(view);

        loginModel = new LoginModel(this);
    }

    //向model发起请求
    public void requestLogin(String cardId,String password){
        getView().showLoading();
        loginModel.logining(cardId,password);
    }

    public void requestChangeIP(String ip,String port){
        //todo
        loginModel.executeRequestChangeIP(ip,port);
    }

    @Override
    public void onSuccess() {
        ((IActivityOne)getView()).showRightToast();
        ((IActivityOne)getView()).onSuccess();
        onComplete();
    }

    @Override
    public void onFailure() {
        ((IActivityOne)getView()).showWrongToast();
        onComplete();
    }


    @Override
    public void onComplete() {
        getView().hideLoading();
    }

    @Override
    public void onSuccessChangeIP() {
        ((IActivityTwo)getView()).onSuccess();
    }


    public void showNotice(String title,String detail){
        //公告通知类
        mNotificationManager = (NotificationManager)(getView().getSelfActivity()).getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(getView().getSelfActivity());


        mBuilder.setContentTitle(title);
        mBuilder.setContentText(detail);
        Notification notification = mBuilder.build();
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
        mBuilder.setDefaults(Notification.DEFAULT_LIGHTS);
        mNotificationManager.notify(1,notification);
    }

    public void onSuccessNotice(List<Notice> list) {
        for(Notice i : list){
            showNotice(i.getTitle(),i.getDetail());
        }
    }
}
