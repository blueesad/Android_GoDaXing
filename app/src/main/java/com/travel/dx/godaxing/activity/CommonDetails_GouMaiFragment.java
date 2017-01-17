package com.travel.dx.godaxing.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.travel.dx.godaxing.R;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class CommonDetails_GouMaiFragment extends BaseFragment {

    private TextView tv_bay;

    @Override
    protected void findViews(View view) {
        tv_bay = (TextView) view.findViewById(R.id.tv_bay);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            tv_bay.setText(bundle.getString("notice"));
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_goumai;
    }
}
