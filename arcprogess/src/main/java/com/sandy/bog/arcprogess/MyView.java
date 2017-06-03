package com.sandy.bog.arcprogess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Sandy Luo on 2017/5/18.
 */

public class MyView extends View{
    private static final int MAX_PM25 = 100;

    private Paint circlePaint;
    private float circleWidth;
    private RectF tempRectF;
    private float progress;
    private int bkgCircleColor, progressCircleColor;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setWillNotDraw(false);

        bkgCircleColor = 0x32FFFFFF;
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circleWidth = 30;
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(circleWidth);
        circlePaint.setStrokeCap(Paint.Cap.ROUND);
        circlePaint.setColor(bkgCircleColor);
//        tempRectF = new RectF();



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        tempRectF =new RectF(circleWidth / 2, circleWidth / 2, getWidth() - circleWidth / 2, getHeight() - circleWidth / 2);
    }

    public void update(int value) {


        progressCircleColor = Color.BLUE;
        progress = value * 1f / MAX_PM25;

        invalidate();
        requestLayout();

    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(tempRectF, 120, 300, false, circlePaint);

        float sweepAngle = 300 * progress;
        if (Float.compare(sweepAngle, 0) < 0)
            sweepAngle = 0;
        else if (Float.compare(sweepAngle, 300) > 0)
            sweepAngle = 300;

        if (progress > 0) {
            circlePaint.setColor(progressCircleColor);
            canvas.drawArc(tempRectF, 120, sweepAngle, false, circlePaint);
        }
    }

    @Override
    public void invalidate() {
        circlePaint.setColor(bkgCircleColor);
        super.invalidate();
    }

    private void initPainters() {
    }


}
