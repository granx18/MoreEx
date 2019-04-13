package com.example.moreex.presenter;

import com.example.moreex.model.LoginModel;
import com.example.moreex.view.BaseView;
import com.example.moreex.view.login.IActivityOne;

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


    public void onSuccess(String data) {
        ((IActivityOne)getView()).showRightToast();
        ((IActivityOne)getView()).onSuccess();
    }


    public void onFailure(String msg) {
        ((IActivityOne)getView()).showWrongToast();
    }

    @Override
        public void onSuccess() {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onComplete() {
        getView().hideLoading();
    }

}
