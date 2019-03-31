package com.example.moreex.presenter;

import com.example.moreex.model.LoginModel;
import com.example.moreex.view.BaseView;
import com.example.moreex.view.login.AActivityOne;
import com.example.moreex.view.login.IActivityOne;

public class LoginPresenter extends BasePresenter implements LoginCallback{

    private LoginModel loginModel;

    public LoginPresenter(BaseView view) {
        super(view);

        loginModel = new LoginModel(this);
    }

    public void requestLogin(String data){
        getView().showLoading();
        loginModel.logining();
    }

    @Override
    public void onSuccess(String data) {
        ((IActivityOne)getView()).showRightToast();
        ((AActivityOne)getView().getSelfActivity()).showActivityThree();
    }

    @Override
    public void onFailure(String msg) {
        ((IActivityOne)getView()).showWrongToast();
    }

    @Override
    public void onComplete() {
        getView().hideLoading();
    }


}
