package com.travel.dx.godaxing.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/25 0025.
 */
public class WelComePagerAdapter extends PagerAdapter {


    private final ImageView[] imageViews;

    public WelComePagerAdapter(ImageView[] imageViews)
    {
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return imageViews.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = imageViews[position];
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(imageViews[position]);
    }
}
