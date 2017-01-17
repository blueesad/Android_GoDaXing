package com.travel.dx.godaxing.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.se7en.utils.SystemUtil;
import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.adapter.WelComePagerAdapter;

public class WelcomeActivity extends BaseActivity {


    private static final String VERSION_CODE = "VERSION_CODE";
    private ViewPager viewPager;
    private Button startBtn;
    private WelComePagerAdapter adapter;
    private LinearLayout dotLayout;
    private ImageView[] imageViews;
    private ImageView ivWelcome;

    @Override
    protected void findViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        startBtn = (Button) findViewById(R.id.start_btn);
        dotLayout = (LinearLayout) findViewById(R.id.dot_layout);
        ivWelcome = (ImageView) findViewById(R.id.iv_welcome);
    }

    @Override
    protected void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            // TODO 滑到最后页就显示开始体验按钮
                changeDot(position);
                if (position == imageViews.length - 1) {
                    startBtn.setVisibility(View.VISIBLE);
                } else {
                    startBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳到 主页
                jumpToMainActivity();
                //将当前应用的版本号设置到sp中，以用作下次对比使用
                SystemUtil.setSharedInt(VERSION_CODE, SystemUtil.getSystemVersionCode());
            }
        });
    }



    @Override
    protected void init() {


        new Handler().postDelayed(new Runnable() {  //欢迎界面延时1.5秒进入
            @Override
            public void run() {
                viewPager.setOffscreenPageLimit(3);
                int oldVersionCode = SystemUtil.getSharedInt(VERSION_CODE, -1);
                int newVersionCode = SystemUtil.getSystemVersionCode();// 获取应用当前的版本号
                if (oldVersionCode == -1 || oldVersionCode < newVersionCode) {
                    ivWelcome.setVisibility(View.GONE);
                    viewPager.setVisibility(View.VISIBLE);
                    dotLayout.setVisibility(View.VISIBLE);
                    initViewPager();
                    initDot(); //初始化小圆点
                } else {
                    viewPager.setVisibility(View.GONE);
                    dotLayout.setVisibility(View.GONE);
                    jumpToMainActivity();

                }
            }
        },1500);

    }

    private void initDot() {
        for (int i = 0; i < imageViews.length; i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0){
                imageView.setImageResource(R.mipmap.dot_selected);
            }else {
                imageView.setImageResource(R.mipmap.dot_normal);
            }
            imageView.setPadding(10,0,10,0);
            dotLayout.addView(imageView);
        }
    }
    private void changeDot(int position) {
        for (int i = 0; i < dotLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) dotLayout.getChildAt(i);
            if (i == position){
                imageView.setImageResource(R.mipmap.dot_selected);
            }else {
                imageView.setImageResource(R.mipmap.dot_normal);
            }

        }
    }

    private void jumpToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void initViewPager() {
        //图片欢迎界面
        imageViews = new ImageView[3];
        ImageView iv = new ImageView(this);
        setImageBitmap(iv, R.mipmap.bg_1);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViews[0] = iv;

        iv = new ImageView(this);
        setImageBitmap(iv, R.mipmap.bg_2);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViews[1] = iv;

        iv = new ImageView(this);
        setImageBitmap(iv, R.mipmap.bg_3);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViews[2] = iv;

        adapter = new WelComePagerAdapter(imageViews);
        viewPager.setAdapter(adapter);
    }

    private void setImageBitmap(ImageView iv, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;//将bitmap宽和高都压缩2倍
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;//解码整张图片
        Bitmap bm = BitmapFactory.decodeResource(getResources(), resId, options);
        iv.setImageBitmap(bm);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_welcome;
    }
}
