package com.sandy.blog.vieweventdemo.up;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sandy.blog.vieweventdemo.R;

/**
 * Created by Sandy Luo on 2017/2/19.
 */

public class ScrollerActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollerImageView scrollerImageView;

    public static final String TAG = ScrollerActivity.class.getSimpleName();

    public static final int MSG_SCROLL_TO = 1;  //消息标识
    public static final int FRAME_COUNT = 30; //帧数
    public static final int DELAYED_TIME = 33;  //延时时间
    private int mCount = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SCROLL_TO:
                    mCount++;
                    if (mCount <= FRAME_COUNT) {
                        float fraction = mCount / (float) FRAME_COUNT;
                        int scrollX = (int) (fraction * 100);
                        scrollerImageView.scrollTo(scrollX, 0);
                        handler.sendEmptyMessageDelayed(MSG_SCROLL_TO, DELAYED_TIME);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
        scrollerImageView = (ScrollerImageView) findViewById(R.id.scroll_view);
        findViewById(R.id.scroller_btn).setOnClickListener(this);
        findViewById(R.id.animator_btn).setOnClickListener(this);
        findViewById(R.id.delay_btn).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scroller_btn:
                scrollerImageView.smoothScrollTo(100, 0);
                break;
            case R.id.animator_btn:
                slowTranslateByAnimator();
                break;
            case R.id.delay_btn:
                translateByHandler();
                break;
        }
    }

    /**
     * 通过延时策略滑动View
     */
    private void translateByHandler() {
        handler.sendEmptyMessageDelayed(MSG_SCROLL_TO, DELAYED_TIME);
    }

    /**
     * 通过动画的方式实现平移
     */
    private void slowTranslateByAnimator() {
        final int startX = 0;
        final int deltaX = 100;
        ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                scrollerImageView.scrollTo(startX + (int) (deltaX * fraction), 0);
            }
        });
        animator.start();
    }
}
