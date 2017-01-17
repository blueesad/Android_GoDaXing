package com.travel.dx.godaxing.modules.home.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseActivity;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.adapter.TrendsRollAdapter;
import com.travel.dx.godaxing.modules.home.bean.TrendsDetailsInfo;
import com.travel.dx.godaxing.modules.home.dao.TrendsDetailsDao;
import com.travel.dx.godaxing.modules.home.dao.TrendsDetailsRullDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TrendsDetailsActivity extends BaseActivity {
    private RollPagerView rollPagerView;
    private String TrendsId;
    private ImageView backTv;
    private ProgressDialog dialog;
    private TrendsDetailsInfo.DataBean dataBean;
    private TextView TitleTv;
    private TextView  TrendsDetails_share;
    private  TextView TrendsDetails_keep;
    private TextView TrendsDetails_time;
    private WebView trendsDetails_webview;
    private RelativeLayout rl_jingdiantuijian;
    private  RelativeLayout rl_yiqiwanlo;
    private TextView tv_show;
    private  TextView tv_show2;


    @Override
    protected void findViews() {
        rollPagerView= (RollPagerView) findViewById(R.id.trendsDetails_pagerView);
        backTv= (ImageView) findViewById(R.id.xiangqing_fanhui);
        TitleTv= (TextView) findViewById(R.id.trendsDetails_title);
        TrendsDetails_share= (TextView) findViewById(R.id.TrendsDetails_share);
        TrendsDetails_keep= (TextView) findViewById(R.id.TrendsDetails_keep);
        TrendsDetails_time= (TextView) findViewById(R.id.TrendsDetails_time);
        trendsDetails_webview= (WebView) findViewById(R.id.trendsDetails_webview);
        rl_jingdiantuijian= (RelativeLayout) findViewById(R.id.rl_jingdiantuijian);
        rl_yiqiwanlo= (RelativeLayout) findViewById(R.id.rl_yiqiwanlo);
        tv_show= (TextView) findViewById(R.id.tv_show);
        tv_show2= (TextView) findViewById(R.id.tv_show2);
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
                if (tv_show.getVisibility()==View.VISIBLE){
                    tv_show.setVisibility(View.GONE);
                    rl_jingdiantuijian.getChildAt(1).setBackgroundResource(R.mipmap.yincang);
                }else{
                    tv_show.setVisibility(View.VISIBLE);
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
        TrendsId=intent.getStringExtra("id");
        Log.e("**", "ID"+TrendsId);
        rollPagerView.setHintView(new ColorPointHintView(this, Color.GREEN, Color.WHITE));
        rollPagerView.setPlayDelay(3000);


    }

    @Override
    protected void loadData() {
        TrendsDetailsRullDao.requestCommendItemList(TrendsId, new BaseCallBack() {
            @Override
            public void success(Object data) {
                final List<TrendsDetailsInfo.DataBean.ImagesBean> dataBean = (List<TrendsDetailsInfo.DataBean.ImagesBean>) data;
                TrendsRollAdapter adapter = new TrendsRollAdapter(rollPagerView, TrendsDetailsActivity.this, dataBean);
                rollPagerView.setAdapter(adapter);
            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(TrendsDetailsActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
        TrendsDetailsDao.requestCommendItemList(TrendsId, new BaseCallBack() {
            @Override
            public void success(Object data) {
                dialog.dismiss();
                dataBean = (TrendsDetailsInfo.DataBean) data;
                TitleTv.setText(dataBean.getTitle());
                TrendsDetails_share.setText(dataBean.getShare());
                TrendsDetails_keep.setText(dataBean.getCollection());
                String time= timedate(dataBean.getPublish_time());
                TrendsDetails_time.setText("2016-"+time);

                trendsDetails_webview.getSettings().setJavaScriptEnabled(true);
                trendsDetails_webview.loadDataWithBaseURL(null,dataBean.getContent(),"text/html", "utf-8", null);



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
        return R.layout.activity_trends_details;
    }
}
