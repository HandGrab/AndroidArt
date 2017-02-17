package com.sandy.blog.androidaart.start;

import android.content.Intent;

import com.sandy.blog.androidaart.*;

/**
 * Created by Sandy Luo on 2017/2/6.
 */

public class FirstActivity extends BaseActivity {
    @Override
    protected void jump() {
        //显示启动
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
