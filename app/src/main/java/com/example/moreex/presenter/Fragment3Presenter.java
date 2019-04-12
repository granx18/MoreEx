package com.example.moreex.presenter;

import android.graphics.Bitmap;

import com.example.moreex.view.BaseView;
import com.example.moreex.view.main.IFragment3;

public class Fragment3Presenter extends BasePresenter implements Fragment3Callback {
    public Fragment3Presenter(BaseView view) {
        super(view);
    }

    public void requestChangePicture(Bitmap bitmap){
        onSuccessChangePicture();
    }

    @Override
    public void onSuccessChangePicture() {
        getView().showToast("更改封面成功");
    }

    public void requestChangePwd(String pwd){
        onSuccessChangePwd();
    }

    @Override
    public void onSuccessChangePwd() {
        getView().showToast("更改密码成功，重新登录");
        ((IFragment3)getView()).onSuccessChangePwd();
    }
}
