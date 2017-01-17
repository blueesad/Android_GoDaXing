package com.travel.dx.godaxing.widget;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.modules.me.activity.LoginActivity;

/**
 * 联系客服对话框
 */
public  class LoginDialog implements View.OnClickListener {
    private static LoginDialog instance;
    private static AlertDialog loginDialog;
    private final View loginDialogView;
    private final Button btn_quxiao;
    private final Button btn_queding;
    private final FragmentActivity activity;

    public static LoginDialog getInstance(FragmentActivity activity){
        instance = new LoginDialog(activity);
        return instance;
    }

    private LoginDialog(FragmentActivity activity){
        this.activity = activity;
        loginDialogView = activity.getLayoutInflater().inflate(R.layout.layout_login_dialog, null);
        loginDialog = new AlertDialog.Builder(activity)
                .setView(loginDialogView)
                .setCancelable(false)
                .create();
        btn_queding = (Button) loginDialogView.findViewById(R.id.btn_queding);
        btn_quxiao = (Button) loginDialogView.findViewById(R.id.btn_quxiao);
        btn_queding.setOnClickListener(this);
        btn_quxiao.setOnClickListener(this);
    }
    public void show(){
        loginDialog.show();
    }
    public void dismiss(){
        loginDialog.dismiss();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_queding:
                Intent intent=new Intent(activity,LoginActivity.class);
                activity.startActivity(intent);
                this.dismiss();
                break;
            case R.id.btn_quxiao:
                Toast.makeText(activity, "取消",Toast.LENGTH_SHORT).show();
                this.dismiss();
                break;
        }
    }


}
