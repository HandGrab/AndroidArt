package com.sandy.blog.androidaart.launch.standard;

import com.sandy.blog.androidaart.BaseActivity;

/**
 * Created by Sandy Luo on 2017/2/5.
 */

public class BActivity extends BaseActivity {
    @Override
    protected void jump() {
        startNewActivity(CActivity.class);
    }
}
