package com.example.moreex.view.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.example.moreex.R;
import com.example.moreex.view.BaseActivity;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class About extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.moreex)
                .setDescription("跑操管理系统学生端 for Android")
                .addItem(new Element().setTitle("Version 0.3"))

                .addGroup("Connect with us")
                .addWebsite("https://git.dev.tencent.com/granx18/MoreEx.git","Coding")

                .addGroup("suiyueliushang")
                .addEmail("2837644648@qq.com")
                .addTwitter("suiyueliushang")
                .addItem(getQQElement("2837644648"))

                .addGroup("Acac1a")
                .addEmail("729354837@qq.com")
                .addTwitter("Granx18")
                .addItem(getQQElement("729354837"))

                .addGroup("特别感谢")
                .addGitHub("https://github.com/long1eu/SpaceTabLayout","SpaceTabLayout")
                .addGitHub("https://github.com/Frank-Zhu/PullZoomView","PullZoomView")
                .addGitHub("https://github.com/markushi/android-circlebutton","CircleButton")

                .create();
        setContentView(aboutPage);
    }

    Element getQQElement(final String number){
        Element qqElement = new Element();
        qqElement.setIconDrawable(R.drawable.qq);
        qqElement.setGravity(Gravity.LEFT);
        qqElement.setValue(number);
        qqElement.setTitle(number);
        qqElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String url = "mqqwpa://im/chat?chat_type=wpa&uin="+number;
                    //uin是发送过去的qq号码
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } catch (Exception e) {
                    e.printStackTrace();
                    showToast("你连QQ都没有吗...");
                }
            }
        });
        return qqElement;
    }

}
