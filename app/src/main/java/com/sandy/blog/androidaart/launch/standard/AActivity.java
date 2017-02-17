package com.sandy.blog.androidaart.launch.standard;

import android.content.Intent;

import com.sandy.blog.androidaart.BaseActivity;

/**
 * Created by Sandy Luo on 2017/2/5.
 */

public class AActivity extends BaseActivity {
    @Override
    protected void jump() {
        Intent intent = new Intent(this, BActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
