package com.sandy.blog.androidaart.start;

import android.content.Intent;

import com.sandy.blog.androidaart.BaseActivity;

/**
 * Created by Sandy Luo on 2017/2/6.
 */

public class SecondActivity extends BaseActivity {
    @Override
    protected void jump() {
        //隐式调用
        Intent intent = new Intent();
        intent.setAction("android.intent.action.art");
        startActivity(intent);

    }
}
