package com.travel.dx.godaxing.modules.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.dx.godaxing.DaXingApplication;
import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseFragment;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.me.bean.LoginInfo;
import com.travel.dx.godaxing.modules.me.dao.ExitDao;
import com.travel.dx.godaxing.widget.LoginDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout loginlayout;
    private   TextView  logintitle;
    private Button btnexit;
    private RelativeLayout jingidanlayout;
    private RelativeLayout playtogetherlayout;
    private RelativeLayout rl_dianzipiao;
    private RelativeLayout rl_dianzipiao1;
    private RelativeLayout rl_jifen;
    private RelativeLayout rl_daxingjuan;
    private RelativeLayout rl_shoucang;
    private RelativeLayout rl_xiangce;
    private RelativeLayout rl_dianping;
    private RelativeLayout rl_ziliao;
    private RelativeLayout rl_mima;
    private RelativeLayout rl_guangyu;


    @Override
    protected void findViews(View view) {
        loginlayout = (RelativeLayout) view.findViewById(R.id.login);
        logintitle= (TextView) view.findViewById(R.id.tv_login);
        btnexit = (Button) view.findViewById(R.id.exit_btn);
        jingidanlayout = (RelativeLayout) view.findViewById(R.id.jingdian);
        playtogetherlayout = (RelativeLayout) view.findViewById(R.id.playtogether);
        rl_dianzipiao = (RelativeLayout) view.findViewById(R.id.rl_dianzipiao);
        rl_jifen = (RelativeLayout) view.findViewById(R.id.rl_jifen);
        rl_daxingjuan = (RelativeLayout) view.findViewById(R.id.rl_daxingjuan);
        rl_shoucang = (RelativeLayout) view.findViewById(R.id.rl_shoucang);
        rl_xiangce = (RelativeLayout) view.findViewById(R.id.rl_xiangce);
        rl_dianping = (RelativeLayout) view.findViewById(R.id.rl_dianping);
        rl_ziliao = (RelativeLayout) view.findViewById(R.id.rl_ziliao);
        rl_mima = (RelativeLayout) view.findViewById(R.id.rl_mima);
        rl_guangyu = (RelativeLayout) view.findViewById(R.id.rl_guangyu);


    }

    @Override
    protected void initEvent() {
        loginlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!DaXingApplication.isLogin){
                    LoginDialog.getInstance(getActivity()).show();
                }else {
                    Intent intent=new Intent(getActivity(),MymeansActivity.class);
                    intent.putExtra("isloadmsg",true);
                   startActivityForResult(intent,4);
                }
            }
        });

       btnexit.setOnClickListener(this);

        jingidanlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(DaXingApplication.isLogin){
                    Toast.makeText(getActivity(), "尚未完善", Toast.LENGTH_SHORT).show();
                }else {
                    LoginDialog.getInstance(getActivity()).show();
                }
            }
        });

        playtogetherlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DaXingApplication.isLogin){
                    Toast.makeText(getActivity(), "尚未完善", Toast.LENGTH_SHORT).show();
                }else {
                    LoginDialog.getInstance(getActivity()).show();
                }
            }
        });
        rl_dianzipiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DaXingApplication.isLogin){
                    Toast.makeText(getActivity(), "尚未完善", Toast.LENGTH_SHORT).show();
                }else {
                    LoginDialog.getInstance(getActivity()).show();
                }
            }
        });
        rl_jifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DaXingApplication.isLogin){
                    Toast.makeText(getActivity(), "尚未完善", Toast.LENGTH_SHORT).show();
                }else {
                    LoginDialog.getInstance(getActivity()).show();
                }
            }
        });
        rl_daxingjuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DaXingApplication.isLogin){
                    Toast.makeText(getActivity(), "尚未完善", Toast.LENGTH_SHORT).show();
                }else {
                    LoginDialog.getInstance(getActivity()).show();
                }
            }
        });

        rl_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DaXingApplication.isLogin){
                    Toast.makeText(getActivity(), "尚未完善", Toast.LENGTH_SHORT).show();
                }else {
                    LoginDialog.getInstance(getActivity()).show();
                }
            }
        });
        rl_xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DaXingApplication.isLogin){
                    Toast.makeText(getActivity(), "尚未完善", Toast.LENGTH_SHORT).show();
                }else {
                    LoginDialog.getInstance(getActivity()).show();
                }
            }
        });
        rl_dianping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DaXingApplication.isLogin){
                    Toast.makeText(getActivity(), "尚未完善", Toast.LENGTH_SHORT).show();
                }else {
                    LoginDialog.getInstance(getActivity()).show();
                }
            }
        });
        rl_ziliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DaXingApplication.isLogin){
                    Toast.makeText(getActivity(), "尚未完善", Toast.LENGTH_SHORT).show();
                }else {
                    LoginDialog.getInstance(getActivity()).show();
                }
            }
        });
        rl_mima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DaXingApplication.isLogin){
                    Toast.makeText(getActivity(), "尚未完善", Toast.LENGTH_SHORT).show();
                }else {
                    LoginDialog.getInstance(getActivity()).show();
                }
            }
        });
        rl_guangyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(),AboutActivity.class));

            }
        });
        rl_dianzipiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DaXingApplication.isLogin){
                    Toast.makeText(getActivity(), "尚未完善", Toast.LENGTH_SHORT).show();
                }else {
                    LoginDialog.getInstance(getActivity()).show();
                }
            }
        });
        rl_dianzipiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DaXingApplication.isLogin){
                    Toast.makeText(getActivity(), "尚未完善", Toast.LENGTH_SHORT).show();
                }else {
                    LoginDialog.getInstance(getActivity()).show();
                }
            }
        });


    }

    @Override
    protected void init() {

        EventBus.getDefault().register(this);//注册eventBus
        UpdateUI(null);
    }

    @Subscribe
    public void UpdateUI(LoginInfo info) {
        if (DaXingApplication.isLogin){
            logintitle.setText("请您补充个人资料");
            btnexit.setVisibility(View.VISIBLE);
        }else {
            logintitle.setText("亲！您还没有登录");
            btnexit.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void loadData() {


    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_me_layout;
    }


    @Override
    public void onClick(View v) {
        ExitDao.requestCommendExit(DaXingApplication.token, new BaseCallBack() {
            @Override
            public void success(Object data) {
                LoginInfo exitInfo= (LoginInfo) data;
                Toast.makeText(getActivity(), exitInfo.getMsg(), Toast.LENGTH_SHORT).show();
                DaXingApplication.isLogin = false;
                UpdateUI(null);
          }
            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(getActivity(), "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
