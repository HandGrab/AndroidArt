package com.sandy.blog.androidaart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sandy Luo on 2017/2/5.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);
        TextView launchMode = (TextView) findViewById(R.id.launch_model_tv);
        Button jump = (Button) findViewById(R.id.btn_jump);
        jump.setOnClickListener(this);
        launchMode.setText(TAG);
        Log.d(TAG, "Launch Mode onCreate "+ TAG);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jump:
                jump();
        }
    }

    protected abstract void jump();

    protected void startNewActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG,"Launch Mode onNewIntent "+ TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Launch Mode onDestroyad "+ TAG);
    }
}
