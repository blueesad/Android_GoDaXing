package com.travel.dx.godaxing.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class MyScrollView extends ScrollView{
    private ScrollChangeUpdateListener listener;
    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.i("info", "l=" + l + ";t=" + t + ";oldl=" + oldl + ";oldt=" + oldt+"  ;height="+getHeight());
        if (listener != null&&getChildAt(0).getHeight()==t+getHeight())
        {
            listener.onScrollBottom();

        }
        if (listener != null&&getHeight()<=t){
            listener.ScrollScreenHeight();
        }
        if (listener != null&&getHeight()>t){
            listener.unScrollScreenHeight();
        }
    }
    public interface ScrollChangeUpdateListener
    {
        void onScrollBottom();
        void ScrollScreenHeight();
        void unScrollScreenHeight();

    }

    public void setListener(ScrollChangeUpdateListener listener)
    {
        this.listener = listener;
    }
}
