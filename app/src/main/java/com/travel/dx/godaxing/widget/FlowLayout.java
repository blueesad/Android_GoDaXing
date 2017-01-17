package com.travel.dx.godaxing.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/10/19.
 */
public class FlowLayout extends ViewGroup {

    private int width;//当前定义的容器的测量宽度
    private int height;//当前定义的容器的测量高度
    private Context context;


    public FlowLayout(Context context) {
        this(context,null,0);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.context = context;
    }

    //描述每一行
    class LineViewContainer{
        List<View> views = new ArrayList<>();
        int widthSum = 0;
        int maxHeight = 0;
    }

    //存放所有的行
    List<LineViewContainer> lineList = new ArrayList<>();

    //视图管理器回调这个方法给所有的子控件返回一个LayoutParams对象
    //默认返回的是ViewGroup.LayoutParams对象，这种对象不能获得边距
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
//        MarginLayoutParams继承ViewGroup.LayoutParams，但是可以获得边距
        return new MarginLayoutParams(context,attrs);
    }

    //测控件的宽高：
    /**
     *
     * @param widthMeasureSpec    包含宽度方向的宽度类型和值
     * @param heightMeasureSpec   包含高度方向的类型和值
     *
     *                            EXACTLY:精确值得类型
     *                            AT_MOST:wrap_content
     *                            UNSPECIFIED:不确定，没有上限，滚动控件中
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //由于onMeasure会多次调用，所以每次调用之前，要清空
        lineList.clear();
        //通知测量容器的子控件
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //获得布局中设置的容器控件的宽度的值
        int widthMaxSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMaxSize = MeasureSpec.getSize(heightMeasureSpec);
        //统计每一行，
        //新的一行
        LineViewContainer line = new LineViewContainer();
        int maxWidthSum = 0;//记录所有行的宽度的最大值：
        int lineHeightSum = 0;//记录所有行的高度和
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            //获得这个控件的宽度和高度，
            //为了获得外边距：外边距跟父容器有关，childView放在ViewGroup中的就不能转成别的布局的LayoutParams
            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int chidHeight = childView.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
            if (childWidth+line.widthSum > widthMaxSize){
                //控件宽度+本行的宽度和>允许的最大宽度的话，新起一行
                //保存之前的行
                //得到已经完成的行的宽度的最大值
                maxWidthSum = maxWidthSum>line.widthSum?maxWidthSum:line.widthSum;
                //得到已经完成的所有行的高度和
                lineHeightSum += line.maxHeight;
                lineList.add(line);
                line = new LineViewContainer();
            }
            //将控件存入当前行，并且记录这一次存入后的宽度和以及最大行高
            line.views.add(childView);
//                宽度和
            line.widthSum = childWidth+line.widthSum;
//                最大行高
            line.maxHeight = line.maxHeight>chidHeight?line.maxHeight:chidHeight;
            //如果当前加的控件是最后一个，也是一行，需要加入行集合，统计行高和宽度和
            if (i == getChildCount()-1){

                //统计行高和宽度和
                maxWidthSum = maxWidthSum>line.widthSum?maxWidthSum:line.widthSum;
                lineHeightSum += line.maxHeight;
                lineList.add(line);
            }

        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        width = (widthMode==MeasureSpec.EXACTLY)?widthMaxSize:maxWidthSum;
        height = (heightMode==MeasureSpec.EXACTLY)?heightMaxSize:lineHeightSum;
        Log.d("print",lineHeightSum + "onMeasure: "+width+"----"+height);

        setMeasuredDimension(width,height);

    }


    //onLayout:用来控制子控件的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int ml=0,mt=0,mr=0,mb=0;


        for (int i = 0; i < lineList.size(); i++) {
            //得到一行的子控件集合
            ml = 0;//每一行的第一个控件的左边在
            List<View> views = lineList.get(i).views;
            for (int j = 0; j < views.size(); j++) {
                View childView = views.get(j);
                MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
                ml += layoutParams.leftMargin;
                mt += layoutParams.topMargin;
                mr = ml+childView.getMeasuredWidth();
                mb = mt+childView.getMeasuredHeight();
                childView.layout(ml,mt,mr,mb);

                //调整左边
                ml = mr+layoutParams.rightMargin;
                //调整上边:减去前一个view的上边距
                mt -= layoutParams.topMargin;
            }
            //画完一行，调整行的上边
            mt = lineList.get(i).maxHeight;
        }

    }
}
