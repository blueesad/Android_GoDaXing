package com.travel.dx.godaxing.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.dx.godaxing.R;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class BaiduMapActivity extends BaseActivity{

    private TextView tv_title;
    private ImageView iv_fanhui;

    @Override
    protected void findViews() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_fanhui = (ImageView) findViewById(R.id.iv_fanhui);
    }

    @Override
    protected void initEvent() {
        iv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        tv_title.setText(title);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_baidumap;
    }
}
