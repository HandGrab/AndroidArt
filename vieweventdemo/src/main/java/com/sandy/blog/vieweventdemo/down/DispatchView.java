package com.sandy.blog.vieweventdemo.down;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by Sandy Luo on 2017/2/20.
 */

public class DispatchView extends ViewGroup {
    public DispatchView(Context context) {
        super(context);
    }

    public DispatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean consume = false;
        if(onInterceptHoverEvent(event)) {
            consume = onTouchEvent(event);
        }else{
            consume = getChildAt(0).dispatchTouchEvent(event);
        }
        return consume;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
