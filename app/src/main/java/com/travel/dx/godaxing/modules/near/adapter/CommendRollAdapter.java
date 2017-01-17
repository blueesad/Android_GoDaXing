package com.travel.dx.godaxing.modules.near.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.dx.godaxing.modules.near.bean.CommendItemInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class CommendRollAdapter extends StaticPagerAdapter {
    private final List<CommendItemInfo.DataBean.ImagesBean> adv;
    private final Context context;


    public CommendRollAdapter(RollPagerView rollPagerView, Context context, List<CommendItemInfo.DataBean.ImagesBean> adv) {
        super();
        this.context = context;
        this.adv = adv;
    }


    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        String url = adv.get(position).getImg_path();
        ImageLoader.getInstance().displayImage(url, imageView);

        return imageView;

    }

    @Override
    public int getCount() {
        return adv.size();
    }
}
