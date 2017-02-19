package com.sandy.blog.vieweventdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    private Button touchBtn;
    private Button scrollBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        touchBtn = (Button) findViewById(R.id.touch_view);
        scrollBtn = (Button) findViewById(R.id.scroll_view);
        touchBtn.setOnClickListener(this);
        scrollBtn.setOnClickListener(this);

        findViewById(R.id.slow_move_view).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.touch_view:
                skipToActivity(TouchActivity.class);
                break;
            case R.id.scroll_view:
                skipToActivity(MoveActivity.class);
                break;
            case R.id.slow_move_view:
                skipToActivity(ScrollerActivity.class);
                break;
        }
    }

    public void skipToActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }


}
