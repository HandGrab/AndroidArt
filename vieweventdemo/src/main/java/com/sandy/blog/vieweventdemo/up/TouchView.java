package com.sandy.blog.vieweventdemo.up;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

/**
 * Created by Sandy Luo on 2017/2/18.
 */

public class TouchView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    public static final String TAG = TouchView.class.getSimpleName();
    private GestureDetector gestureDetector;


    public TouchView(Context context) {
        super(context);
        init(context);
    }

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     * @param context
     */
    private void init(Context context) {
        gestureDetector = new GestureDetector(context,this);
        //解决长按后无法拖拽的问题
        gestureDetector.setIsLongpressEnabled(false);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return setGesture(event);

//        setVelocity(event);

//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                //getX()/getY()与getRawX()/getRaxY()的区别
//                Log.d(TAG, "x:" + event.getX() + ",y:" + event.getY() + "-----rawX:" + event.getRawX() + ",rawY:" + event.getRawY());
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.d(TAG, "ACTION_MOVE");
//
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
//        return true;
    }

    /**
     * GestureDetector的用法
     */
    private boolean setGesture(MotionEvent event) {
        boolean consume = gestureDetector.onTouchEvent(event);
        return consume;
    }


    /**
     * VelocityTracker的用法
     */
    private void setVelocity(MotionEvent event) {
        //View追踪时间的速度
        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        int xVelocity = (int) velocityTracker.getXVelocity();
        int yVelocity = (int) velocityTracker.getXVelocity();
        Log.d(TAG, "横向速度：" + xVelocity + ",纵向速度：" + yVelocity);

        //释放
        velocityTracker.clear();
        velocityTracker.recycle();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
