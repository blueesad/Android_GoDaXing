package com.travel.dx.godaxing.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.travel.dx.godaxing.R;


/**
 * Created by Administrator on 2016/10/25 0025.
 */
public class BottomMenu extends RelativeLayout {

    private static final float FACTOR = 0.4f;
    private final ImageView ivMenu;
    private final TextView tvMennu;
    private final String menuText;
    private final int resNorId;
    private final int resSeleId;
    private Fragment fragment;

    public BottomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.layout_bottom_menu, this);
        ivMenu = (ImageView) findViewById(R.id.menu_iv);
        tvMennu = (TextView) findViewById(R.id.menu_tv);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.BottomMenu);
        menuText = array.getString(R.styleable.BottomMenu_menu_text);
        resNorId = array.getResourceId(R.styleable.BottomMenu_menu_icon_normal, R.mipmap.ic_launcher);
        resSeleId = array.getResourceId(R.styleable.BottomMenu_menu_icon_selected, R.mipmap.ic_launcher);
        ivMenu.setImageResource(resNorId);
        tvMennu.setText(menuText);
    }

    public void selectMenu(){
        tvMennu.setVisibility(GONE);
        ivMenu.setImageResource(resSeleId);
        updateView(true);
    }
    public void unSelectMenu(){
        tvMennu.setVisibility(VISIBLE);
        ivMenu.setImageResource(resNorId);
        updateView(false);
    }
    private void updateView(boolean flag) {
        ObjectAnimator animator = null;
        if (flag){
            animator = ObjectAnimator.ofFloat(this,"xxx",0,1).setDuration(200);
        }else{
            animator = ObjectAnimator.ofFloat(this,"xxx",1,0).setDuration(200);
        }
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                setPivotX(getWidth()/2);
                setPivotY(0);
                setScaleX(1+FACTOR * progress);
                setScaleY(1+FACTOR * progress);

            }
        });
        animator.start();
    }

    public Fragment getFragment(){
        return fragment;
    }
    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
