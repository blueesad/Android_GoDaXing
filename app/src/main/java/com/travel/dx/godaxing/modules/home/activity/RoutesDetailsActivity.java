package com.travel.dx.godaxing.modules.home.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseActivity;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.adapter.RoutesRollAdapter;
import com.travel.dx.godaxing.modules.home.bean.RoutesDetailsInfo;
import com.travel.dx.godaxing.modules.home.dao.RoutesDetailsDao;
import com.travel.dx.godaxing.modules.home.dao.RoutesDetailsRullDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RoutesDetailsActivity extends BaseActivity{
    private String RoutesId;
    private ImageView backTv;
    private ProgressDialog dialog;
    private RoutesDetailsInfo.DataBean dataBean;
    private TextView TitleTv;
    private TextView RoutesDetails_share;
    private  TextView RoutesDetails_keep;
    private TextView RoutesDetails_time;
    private WebView RoutesDetails_webview;
    private RollPagerView rollPagerView;
    private RelativeLayout rl_jingdiantuijian;
    private  RelativeLayout rl_yiqiwanlo;
    private ListView listView;
    private TextView tv_show2;

    @Override
    protected void findViews() {
        backTv= (ImageView) findViewById(R.id.routesDetails_fanhui);
        TitleTv= (TextView) findViewById(R.id.routesDetails_title);
        rollPagerView= (RollPagerView) findViewById(R.id.routesDetails_pagerView);
        RoutesDetails_share= (TextView) findViewById(R.id.routesDetails_share);
        RoutesDetails_keep= (TextView) findViewById(R.id.routesDetails_keep);
        RoutesDetails_time= (TextView) findViewById(R.id.routesDetails_time);
        RoutesDetails_webview= (WebView) findViewById(R.id.routesDetails_webview);
        listView= (ListView) findViewById(R.id.lv_show);
        rl_jingdiantuijian= (RelativeLayout) findViewById(R.id.rl1_jingdiantuijian);
        rl_yiqiwanlo= (RelativeLayout) findViewById(R.id.rl1_yiqiwanlo);
        listView= (ListView) findViewById(R.id.lv_show);
        tv_show2= (TextView) findViewById(R.id.tv_show3);
    }

    @Override
    protected void initEvent() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rl_jingdiantuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView.getVisibility()==View.VISIBLE){
                    listView.setVisibility(View.GONE);
                    rl_jingdiantuijian.getChildAt(1).setBackgroundResource(R.mipmap.yincang);
                }else{
                    listView.setVisibility(View.VISIBLE);
                    rl_jingdiantuijian.getChildAt(1).setBackgroundResource(R.mipmap.zhankai);
                }
            }
        });
        rl_yiqiwanlo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_show2.getVisibility()==View.VISIBLE){
                    tv_show2.setVisibility(View.GONE);
                    rl_yiqiwanlo.getChildAt(1).setBackgroundResource(R.mipmap.yincang);
                }else{
                    tv_show2.setVisibility(View.VISIBLE);
                    rl_yiqiwanlo.getChildAt(1).setBackgroundResource(R.mipmap.zhankai);
                }
            }
        });

    }

    @Override
    protected void init() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("疯狂加载中");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        Intent intent =getIntent();
        RoutesId=intent.getStringExtra("id");
        Log.e("**", "ID"+RoutesId);
        rollPagerView.setHintView(new ColorPointHintView(this, Color.GREEN, Color.WHITE));
        rollPagerView.setPlayDelay(3000);
    }

    @Override
    protected void loadData() {
       RoutesDetailsRullDao.requestCommendItemList(RoutesId, new BaseCallBack() {
            @Override
            public void success(Object data) {
                final List<RoutesDetailsInfo.DataBean.ImagesBean> dataBean = (List<RoutesDetailsInfo.DataBean.ImagesBean>) data;
                RoutesRollAdapter adapter = new RoutesRollAdapter(rollPagerView, RoutesDetailsActivity.this, dataBean);
                rollPagerView.setAdapter(adapter);

            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(RoutesDetailsActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
        RoutesDetailsDao.requestCommendItemList(RoutesId, new BaseCallBack() {
            @Override
            public void success(Object data) {
                dialog.dismiss();
                dataBean = (RoutesDetailsInfo.DataBean) data;
                TitleTv.setText(dataBean.getTitle());
                RoutesDetails_share.setText(dataBean.getShare());
                RoutesDetails_keep.setText(dataBean.getCollection());
                String time= timedate(dataBean.getPublish_time());
                RoutesDetails_time.setText("2016-"+time);
                RoutesDetails_webview.getSettings().setJavaScriptEnabled(true);
                RoutesDetails_webview.loadDataWithBaseURL(null,dataBean.getContent(),"text/html", "utf-8", null);
            }
            @Override
            public void failed(int errorCode, Object data) {
                dialog.dismiss();
            }
        });


    }
    private String timedate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM-dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_routes_details;
    }
}
