package com.sandy.blog.androidaart.launch.standard;

import com.sandy.blog.androidaart.BaseActivity;

/**
 * Created by Sandy Luo on 2017/2/5.
 */

public class CActivity extends BaseActivity {
    @Override
    protected void jump() {
        startNewActivity(DActivity.class);
    }
}
