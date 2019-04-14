package com.example.moreex.presenter;

import com.example.moreex.model.LoginModel;
import com.example.moreex.view.BaseView;
import com.example.moreex.view.login.IActivityOne;
import com.example.moreex.view.login.IActivityTwo;

import java.util.List;

import io.swagger.client.model.Notice;

public class LoginPresenter extends BasePresenter implements LoginCallback{

    private LoginModel loginModel;

    public LoginPresenter(BaseView view) {
        super(view);

        loginModel = new LoginModel(this);
    }

    //向model发起请求
    public void requestLogin(String cardId,String password){
        getView().showLoading();
        loginModel.logining(cardId,password);
    }

    public void requestNotice(){
        //todo
        loginModel.executeRequestNotice();
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
    public void onSuccessNotice(List<Notice> list) {
        ((IActivityOne)getView()).onSuccessNotice(list);
    }

    @Override
    public void onSuccessChangeIP() {
        ((IActivityTwo)getView()).onSuccess();
    }
}
