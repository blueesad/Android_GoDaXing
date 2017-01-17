package com.travel.dx.godaxing.modules.me.activity;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseFragment;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.me.bean.LoginInfo;
import com.travel.dx.godaxing.modules.me.dao.CodeDao;
import com.travel.dx.godaxing.modules.me.dao.RegisterDao;
import com.travel.dx.godaxing.modules.me.util.IsMobileNo;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class RegisterFragment extends BaseFragment {
    private EditText et_phone;
    private Button btn_yanzhengma;
    private String mobile;
    private int second = 60;
    private Handler myHandler;
    private Thread thread;
    private EditText et_yanzhengma;
    private EditText et_password;
    private String code;
    private String pwd;
    private Button btn_register;

    @Override
    protected void findViews(View view) {
        et_phone = (EditText) view.findViewById(R.id.et_phone);
        btn_yanzhengma = (Button) view.findViewById(R.id.btn_yanzhengma);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        et_yanzhengma = (EditText) view.findViewById(R.id.et_yanzhengma);
        et_password = (EditText) view.findViewById(R.id.et_password);
    }

    @Override
    protected void initEvent() {
        btn_yanzhengma.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mobile = et_phone.getText().toString().trim();
                if (!IsMobileNo.isMobileNo(mobile)) {
                    Toast.makeText(getActivity(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                } else {
                    SecondStart();
                    getCode(mobile);
                }
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                code = et_yanzhengma.getText().toString();
                pwd = et_password.getText().toString();
                toRegister(pwd,code,mobile);

            }
        });
    }

    private void toRegister(String pwd, String code, String mobile) {
        RegisterDao.requestRegister(pwd, code, mobile, new BaseCallBack() {
            @Override
            public void success(Object data) {
                LoginInfo register = (LoginInfo) data;
                Toast.makeText(getActivity(), register.getMsg(), Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(getActivity(), "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SecondStart() {
        //1.创建类继承Handler,重写处理消息的方法
        class MyHandler extends Handler {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    btn_yanzhengma.setText(second + "秒后重新发送");
                    btn_yanzhengma.setEnabled(false);
                } else if (msg.what == 2) {
                    btn_yanzhengma.setEnabled(true);
                    btn_yanzhengma.setText("获取验证码");
                    second = 60;
                }
            }
        }
                //2.创建Handler的对象
                myHandler = new MyHandler();
        new Thread(new Runnable() {
            private Message message;

            @Override
            public void run() {
                while (second != 0) {
                    SystemClock.sleep(1000);
                    second--;
                    message = new Message();
                    message.what = 1;
                    myHandler.sendMessage(message);
                }
                message = new Message();
                message.what = 2;

                myHandler.sendMessage(message);

            }
        }).start();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_register;
    }

    public void getCode(String mobile) {
        CodeDao.requestCode(mobile, new BaseCallBack() {
            @Override
            public void success(Object data) {
                LoginInfo code = (LoginInfo) data;
                Toast.makeText(getActivity(), code.getMsg(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(getActivity(), data.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
