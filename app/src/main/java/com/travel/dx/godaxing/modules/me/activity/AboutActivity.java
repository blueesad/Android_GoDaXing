package com.travel.dx.godaxing.modules.me.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class AboutActivity extends BaseActivity {

    private RelativeLayout rl_kefu;
    private RelativeLayout rl_down;
    private AlertDialog kefuDialog;
    private AlertDialog usDialog;
    private View kefuDialogView;
    private ImageView iv_fanhui;
    private Button btn_queding;
    private Button btn_quxiao;
    private RelativeLayout rl_about;
    private View usDialogView;
    private TextView tv_name1;
    private List<String> name = new ArrayList<>();
    private TextView tv_name2;
    private TextView tv_name3;
    private TextView tv_name4;
    private Button us_queding;

    @Override
    protected void findViews() {
        rl_kefu = (RelativeLayout) findViewById(R.id.rl_kefu);
        rl_down = (RelativeLayout) findViewById(R.id.rl_down);
        rl_about = (RelativeLayout) findViewById(R.id.rl_about);
        iv_fanhui = (ImageView) findViewById(R.id.iv_fanhui);
        kefuDialogView = getLayoutInflater().inflate(R.layout.layout_kefu_dialog, null);
        btn_queding = (Button) kefuDialogView.findViewById(R.id.btn_queding);
        btn_quxiao = (Button) kefuDialogView.findViewById(R.id.btn_quxiao);
        usDialogView = getLayoutInflater().inflate(R.layout.layout_us_dialog, null);
        us_queding = (Button) usDialogView.findViewById(R.id.btn_queding);
        tv_name1 = (TextView) usDialogView.findViewById(R.id.tv_name1);
        tv_name2 = (TextView) usDialogView.findViewById(R.id.tv_name2);
        tv_name3 = (TextView) usDialogView.findViewById(R.id.tv_name3);
        tv_name4 = (TextView) usDialogView.findViewById(R.id.tv_name4);
    }

    @Override
    protected void initEvent() {
        rl_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(name);
                tv_name1.setText(name.get(0));
                tv_name2.setText(name.get(1));
                tv_name3.setText(name.get(2));
                tv_name4.setText(name.get(3));
                usDialog.show();

            }
        });
        rl_kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kefuDialog.show();
            }
        });
        iv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri teluri = Uri.parse("tel:4008191937");
                intent.setData(teluri);
                startActivity(intent);
                kefuDialog.dismiss();
            }
        });
        us_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usDialog.dismiss();
            }
        });
        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kefuDialog.dismiss();

            }
        });
        rl_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutActivity.this, "您当前的版本已是最新版本", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void init() {
        //联系客服对话框
        kefuDialog = new AlertDialog.Builder(this)
                .setView(kefuDialogView)
                .setCancelable(false)
                .create();
        usDialog = new AlertDialog.Builder(this)
                .setView(usDialogView)
                .setCancelable(false)
                .create();
        name.add("贺帅");
        name.add("郭伟");
        name.add("钟佳杰");
        name.add("余少康");
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_about;
    }
}
