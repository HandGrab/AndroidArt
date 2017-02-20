package com.sandy.blog.vieweventdemo.up;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Scroller;

/**
 * Created by Sandy Luo on 2017/2/18.
 */

public class ScrollerImageView extends ImageView {

    private Scroller mScroller;

    public ScrollerImageView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
    }

    public ScrollerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 滑动到指定的位置
     *
     * @param destX 横坐标的位置
     * @param destY 纵坐标的位置
     */
    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        //1秒内滑向destX
        mScroller.startScroll(scrollX, 0, delta, 0, 3000);
        invalidate();
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
