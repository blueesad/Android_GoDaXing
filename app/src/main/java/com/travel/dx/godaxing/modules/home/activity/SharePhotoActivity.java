package com.travel.dx.godaxing.modules.home.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseActivity;

public class SharePhotoActivity extends BaseActivity {
    private RadioGroup radioGroup;
    private PhotoCommendFragment commend;
    private PhotoTogetherFragment together;
    private ImageView photo_fanhui;

    @Override
    protected void findViews() {
        radioGroup = (RadioGroup)findViewById(R.id.photo_radiogroup);
        photo_fanhui = (ImageView) findViewById(R.id.photo_fanhui);
    }

    @Override
    protected void initEvent() {
        photo_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.photo_tuijian){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.photo_container_layout,commend);

                    transaction.commit();
                }
                if (checkedId == R.id.photo_together){

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.photo_container_layout,together);

                    transaction.commit();
                }
            }
        });

    }

    @Override
    protected void init() {
        commend=new PhotoCommendFragment();
        together=new PhotoTogetherFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.photo_container_layout, commend);
        transaction.add(R.id.photo_container_layout, together);
        transaction.hide(together);
        transaction.commit();
    }
    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_share_photo;
    }
}
