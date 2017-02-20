package com.sandy.blog.vieweventdemo.up;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.sandy.blog.vieweventdemo.R;

/**
 * Created by Sandy Luo on 2017/2/18.
 */

public class MoveActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MoveActivity.class.getSimpleName();

    private ImageView scrollerImageView;
    private int mLastX = 0;
    private int mLastY = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);


        findViewById(R.id.scroll_by_btn).setOnClickListener(this);
        findViewById(R.id.scroll_to_btn).setOnClickListener(this);
        findViewById(R.id.tween_animation_btn).setOnClickListener(this);
        findViewById(R.id.attribute_animation_btn).setOnClickListener(this);
        findViewById(R.id.change_layout_params_btn).setOnClickListener(this);


        scrollerImageView = (ImageView) findViewById(R.id.scroll_iv);
        scrollerImageView.setOnClickListener(this);

        setImageViewListener();

//        scrollerImageView.smoothScrollTo(200, 200);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scroll_view:
                Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scroll_by_btn:
                scrollerImageView.scrollBy(100, 0);
                break;
            case R.id.scroll_to_btn:
                scrollerImageView.scrollTo(100, 0);
                break;
            case R.id.tween_animation_btn:
                //补间移动
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.translation_anim);
                scrollerImageView.startAnimation(animation);
                break;
            case R.id.attribute_animation_btn:
                //属性动画
                ObjectAnimator.ofFloat(scrollerImageView, "translationY", 0, 300).setDuration(1000).start();
                break;
            case R.id.change_layout_params_btn:
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) scrollerImageView.getLayoutParams();
                params.height += 100;
                params.topMargin += 100;
                scrollerImageView.requestLayout();//或者 scrollerImageView.setLayoutParams(params);
                break;
        }
    }


    /**
     * 设置图片的监听事件
     */
    private void setImageViewListener() {
        scrollerImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getRawX();
                int y = (int) event.getRawY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int deltaX = x - mLastX;
                        int deltaY = y - mLastY;
                        int translationX = (int) scrollerImageView.getTranslationX();
                        int translationY = (int) scrollerImageView.getTranslationY();


                        Log.d(TAG, "dX:" + deltaX + ",dY:" + deltaY + ",translationX:" + translationX + ",translationY:" + translationY);
                        scrollerImageView.setTranslationX(translationX + deltaX);
                        scrollerImageView.setTranslationY(translationY + deltaY);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        break;
                }

                mLastX = x;
                mLastY = y;

                Log.d(TAG, "view top:" + scrollerImageView.getTop());
                return true;
            }
        });
    }
}
