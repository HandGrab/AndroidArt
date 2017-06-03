package com.sandy.bog.arcprogess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Locale;

/**
 * Author： liyi
 * Date：    2017/3/17.
 */

public class DetectorCirclePanel extends FrameLayout {

    public static final String TAG = DetectorCirclePanel.class.getSimpleName();

    private static final int MAX_PM25 = 100;
    private TextView tvLevel;
    private TextView tvValue;
    private TextView tvUnit;
    private Paint circlePaint;
    private float circleWidth ;
    private RectF tempRectF;
    private float progress;
    private int bkgCircleColor = 0x32FFFFFF;
    private int progressCircleColor;

    public DetectorCirclePanel(Context context) {
        this(context, null);
    }

    public DetectorCirclePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        LayoutInflater.from(context).inflate(R.layout.layout_detector_circle_panel, this, true);

        tvLevel = (TextView) findViewById(R.id.tv_level);
        tvValue = (TextView) findViewById(R.id.tv_value);
        tvValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60);
        tvUnit = (TextView) findViewById(R.id.tv_unit);



//        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        initPainters();
        tempRectF = new RectF();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        tempRectF.set(circleWidth / 2, circleWidth / 2, getWidth() - circleWidth / 2, getHeight() - circleWidth / 2);
    }

    private void initPainters() {
        circleWidth = 30;
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(bkgCircleColor);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(circleWidth);
        circlePaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    public void invalidate() {
        initPainters();
        super.invalidate();
    }

    public void update(int value) {
        tvValue.setText(String.format(Locale.getDefault(), "%d", value));
        progressCircleColor = Color.YELLOW;
        progress = value * 1f / MAX_PM25;
        invalidate();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int gap;
        int centerX, centerY;
        int halfWidth, halfHeight;

        centerX = getWidth() / 2;
//        gap = CommonUtil.dip2px(getContext(),28);
        gap = 72;

        centerY = getHeight() / 2;
        halfWidth = tvValue.getMeasuredWidth() / 2;
        halfHeight = tvValue.getMeasuredHeight() / 2;
        tvValue.layout(centerX - halfWidth, centerY - halfHeight, centerX + halfWidth, centerY + halfHeight);

        centerY = (tvValue.getTop() + gap) / 2;
        halfWidth = tvLevel.getMeasuredWidth() / 2;
        halfHeight = tvLevel.getMeasuredHeight() / 2;
        tvLevel.layout(centerX - halfWidth, centerY - halfHeight, centerX + halfWidth, centerY + halfHeight);

        centerY = (tvValue.getBottom() + getHeight() - gap) / 2;
        halfWidth = tvUnit.getMeasuredWidth() / 2;
        halfHeight = tvUnit.getMeasuredHeight() / 2;
        tvUnit.layout(centerX - halfWidth, centerY - halfHeight, centerX + halfWidth, centerY + halfHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        if (!hasDraw) {

            canvas.drawArc(tempRectF, 120, 300, false, circlePaint);
//        }


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

}
