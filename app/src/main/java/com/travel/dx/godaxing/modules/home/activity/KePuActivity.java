package com.travel.dx.godaxing.modules.home.activity;

import android.view.View;
import android.widget.ImageView;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseActivity;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class KePuActivity extends BaseActivity {
    private ImageView ivReturn;

    @Override
    protected void findViews() {
        ivReturn = (ImageView) findViewById(R.id.iv_fanhui);

    }

    @Override
    protected void initEvent() {
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_kepu;
    }
}
