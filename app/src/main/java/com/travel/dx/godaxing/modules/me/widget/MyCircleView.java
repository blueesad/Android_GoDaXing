package com.travel.dx.godaxing.modules.me.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by asus on 2016/10/20.
 */
public class MyCircleView extends ImageView {
    private Paint paint;

    public MyCircleView(Context context) {
        this(context,null);
    }

    public MyCircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取用于用src设置的图片，如果是Bitmap，就自己处理
        Drawable drawable = getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable){
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap srcBitmap = bitmapDrawable.getBitmap();
            //将原图变成圆图
            Bitmap circleBitmap = getCircleBitmap(srcBitmap);


            //再将圆图绘制在控件的画布（控件的画布）上：
            Rect srcRect = new Rect(0,0,circleBitmap.getWidth(),circleBitmap.getHeight());

            Rect dstRect = null;
            if (getWidth()>getHeight()){
                //控件的宽度大于高度，目标区域
                dstRect = new Rect(getWidth()/2-getHeight()/2,0,getWidth()/2+getHeight()/2,getHeight());
            }else {
                dstRect = new Rect(0,getHeight()/2-getWidth()/2,getWidth(),getHeight()/2+getWidth()/2);
            }
            canvas.drawBitmap(circleBitmap,srcRect,dstRect,null);

        }else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getCircleBitmap(Bitmap srcBitmap) {
        //创建一张空白的图:宽度=高度=原图的宽高的最小值
        int srcWidth = srcBitmap.getWidth();
        int srcHeight = srcBitmap.getHeight();
        int destWidth = Math.min(srcHeight,srcWidth);
        int destHeight = destWidth;
        Bitmap circleBitmap = Bitmap.createBitmap(destWidth,destHeight, Bitmap.Config.ARGB_8888);
        //创建画布:在这张画布上绘制的内容就是绘制在circleBitmap上的
        Canvas canvas = new Canvas(circleBitmap);

        //先画一个实心圆：圆心在画布的中心----dst
        canvas.drawCircle(destWidth/2,destHeight/2,destHeight/2,paint);

        //设置画笔的混合模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));


        //绘制原图：
        Rect srcRect = new Rect(0,0,srcWidth,srcHeight);//原图的范围
        Rect dstRect = new Rect(0,0,destWidth,destHeight);
        canvas.drawBitmap(srcBitmap,srcRect,dstRect,paint);

        return circleBitmap;
    }
}
