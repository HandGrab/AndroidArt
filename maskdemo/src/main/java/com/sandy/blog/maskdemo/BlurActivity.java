package com.sandy.blog.maskdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import jp.wasabeef.blurry.Blurry;

/**
 * Created by Sandy Luo on 2017/4/8.
 */

public class BlurActivity extends AppCompatActivity{

    boolean isBlur = false;

    private RelativeLayout mRlDemo;
    private Button mImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mRlDemo = (RelativeLayout) findViewById(R.id.demo_rl);
        mImage = (Button) findViewById(R.id.demo_image);


        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isBlur) {
                    Blurry.with(BlurActivity.this).radius(25).sampling(2).async().animate(500).onto(mRlDemo);
                    isBlur = true;
                }else{
                    Blurry.delete(mRlDemo);
                    isBlur = false;
                }
            }
        });

    }





}
