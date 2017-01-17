package com.travel.dx.godaxing.modules.me.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseActivity;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class LoginActivity extends BaseActivity {

    private ImageView iv_fanhui;
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private RadioGroup radioGroup;

    @Override
    protected void findViews() {
        iv_fanhui = (ImageView) findViewById(R.id.iv_fanhui);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

    @Override
    protected void initEvent() {
        iv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = null;
                switch (checkedId) {
                    case R.id.rb_login:
                        transaction = getSupportFragmentManager().beginTransaction();
                        if (loginFragment != null) {
                            transaction.replace(R.id.fragment_container, loginFragment);
                        }

                        transaction.commit();
                        break;
                    case R.id.rb_register:
                        transaction = getSupportFragmentManager().beginTransaction();
                        if (registerFragment != null) {
                            transaction.replace(R.id.fragment_container, registerFragment);
                        }
                        transaction.commit();
                        break;

                }
            }
        });
    }

    @Override
    protected void init() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        transaction.add(R.id.fragment_container, loginFragment);
        transaction.commit();

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }
}
