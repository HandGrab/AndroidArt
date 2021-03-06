package com.sandy.bog.arcprogess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Sandy Luo on 2017/5/18.
 */

public class BgView extends View{

    private Paint circlePaint;
    private float circleWidth;
    private RectF tempRectF;

    private int bkgCircleColor;

    public BgView(Context context) {
        this(context, null);
    }

    public BgView(Context context, AttributeSet attrs) {
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
        tempRectF = new RectF();


    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        tempRectF.set(circleWidth / 2, circleWidth / 2, getWidth() - circleWidth / 2, getHeight() - circleWidth / 2);
        canvas.drawArc(tempRectF, 120, 300, true, circlePaint);

    }
}
