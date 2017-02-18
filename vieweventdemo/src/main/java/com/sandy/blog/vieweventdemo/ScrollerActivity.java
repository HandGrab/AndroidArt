package com.sandy.blog.vieweventdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sandy Luo on 2017/2/18.
 */

public class ScrollerActivity extends AppCompatActivity implements View.OnClickListener{

    private Button scroll_by_btn;
    private Button scroll_to_btn;
    private Button animation_btn;
    private ScrollerImageView scrollerImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);

        scroll_by_btn = (Button) findViewById(R.id.scroll_by_btn);
        scroll_to_btn = (Button) findViewById(R.id.scroll_to_btn);
        animation_btn = (Button) findViewById(R.id.animation_btn);

        scroll_by_btn.setOnClickListener(this);
        scroll_to_btn.setOnClickListener(this);
        animation_btn.setOnClickListener(this);


        scrollerImageView = (ScrollerImageView) findViewById(R.id.scroll_iv);
//        scrollerImageView.smoothScrollTo(200, 200);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scroll_by_btn:
                scrollerImageView.scrollBy(100,0);
                break;
            case R.id.scroll_to_btn:
                scrollerImageView.scrollTo(100,0);
                break;
            case R.id.animation_btn:
                break;
        }
    }
}
