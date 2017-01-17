package com.travel.dx.godaxing.modules.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.dx.godaxing.DaXingApplication;
import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseFragment;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.me.bean.LoginInfo;
import com.travel.dx.godaxing.modules.me.dao.LoginDao;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class LoginFragment extends BaseFragment {
    private EditText username;
    private EditText password;
    private Button loginbtn;
    private String Textusername;
    private String Textpassword;
    private TextView tv_findpwd;

    @Override
    protected void findViews(View view) {
        username = (EditText) view.findViewById(R.id.et_username);
        password = (EditText) view.findViewById(R.id.et_password);
        loginbtn = (Button) view.findViewById(R.id.login_btn);
        tv_findpwd = (TextView) view.findViewById(R.id.tv_findpwd);
    }

    @Override
    protected void initEvent() {
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Textusername = username.getText().toString().trim();
                Textpassword = password.getText().toString().trim();
                toLogin();
            }


        });
        tv_findpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FindPwdActivity.class);
                startActivity(intent);
            }
        });
    }

    private void toLogin() {
        LoginDao.requestCommendLogin(Textusername, Textpassword, new BaseCallBack() {
            @Override
            public void success(Object data) {
                LoginInfo login = (LoginInfo) data;
                if( login.getMsg() != null){
                    Toast.makeText(getActivity(), login.getMsg(), Toast.LENGTH_SHORT).show();
                }
                if (login.getStatus() == 1){
                    DaXingApplication.token = login.getToken().toString();
                    DaXingApplication.isLogin = true;
                    EventBus.getDefault().post(new LoginInfo());
                }else {
                    DaXingApplication.isLogin = false;
                }
                getActivity().finish();

            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(getActivity(), "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
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
        return R.layout.fragment_login;
    }
}
