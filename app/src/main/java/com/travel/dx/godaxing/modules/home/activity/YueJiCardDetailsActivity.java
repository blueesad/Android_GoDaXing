package com.travel.dx.godaxing.modules.home.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
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
import com.travel.dx.godaxing.modules.home.adapter.YueJiCardRollAdapter;
import com.travel.dx.godaxing.modules.home.bean.YueJiCardDetailsInfo;
import com.travel.dx.godaxing.modules.home.dao.YueJiCardDetailsDao;
import com.travel.dx.godaxing.modules.home.dao.YueJiCardDetailsRollDao;

import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class YueJiCardDetailsActivity extends BaseActivity {


    private ImageView back;
    private ImageView share;
    private ProgressDialog dialog;
    private RollPagerView rollPagerView;
    private String yuejiID;
    private WebView webView;
    private TextView name;
    private TextView price;
    private TextView address;
    private TextView tips;
    private TextView transportation;
    private RelativeLayout tese;
    private RelativeLayout jiaotong;
    private TextView sharetv;
    private TextView keeptv;
    private OnekeyShare oks = new OnekeyShare();

    @Override
    protected void findViews() {
        rollPagerView = (RollPagerView) findViewById(R.id.yue_ji_item_view_pager);
        back = (ImageView) findViewById(R.id.iv_fanhui);
        share = (ImageView) findViewById(R.id.iv_share);
        webView = (WebView) findViewById(R.id.yue_ji_details_webview);
        name = (TextView) findViewById(R.id.yue_ji_details_name);
        price = (TextView) findViewById(R.id.yue_ji_details_price);
        address = (TextView) findViewById(R.id.yue_ji_details_address);
        tips = (TextView) findViewById(R.id.yue_ji_details_tips);
        transportation = (TextView) findViewById(R.id.yue_ji_item_transportation);
        tese = (RelativeLayout) findViewById(R.id.yue_ji_details_tese);
        jiaotong = (RelativeLayout) findViewById(R.id.yue_ji_details_jiaotong);
        sharetv = (TextView) findViewById(R.id.commend_item_share);
        keeptv = (TextView) findViewById(R.id.commend_item_keep);
    }

    @Override
    protected void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        tese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.getVisibility() == View.VISIBLE) {
                    webView.setVisibility(View.GONE);
                    tese.getChildAt(1).setBackgroundResource(R.mipmap.yincang);
                } else {
                    webView.setVisibility(View.VISIBLE);
                    tese.getChildAt(1).setBackgroundResource(R.mipmap.zhankai);
                }
            }
        });
        jiaotong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (transportation.getVisibility() == View.VISIBLE) {
                    transportation.setVisibility(View.GONE);
                    jiaotong.getChildAt(1).setBackgroundResource(R.mipmap.yincang);
                } else {
                    transportation.setVisibility(View.VISIBLE);
                    jiaotong.getChildAt(1).setBackgroundResource(R.mipmap.zhankai);
                }
            }
        });
    }

    private void showShare() {
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(name.getText().toString());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.bytravel.cn/view/index3469.html");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("Go大兴是一款为广大旅游爱好者提供丰富的旅游资源的应用，让您愉快出行，满意而归。");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        //            oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数

        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.bytravel.cn/view/index3469.html");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("Go大兴是一款为广大旅游爱好者提供丰富的旅游资源的应用，让您愉快出行，满意而归。");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("Go大兴");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.bytravel.cn/view/index3469.html");

        // 启动分享GUI
        oks.show(this);

    }

    @Override
    protected void init() {

        dialog = new ProgressDialog(this);
        dialog.setMessage("疯狂加载中");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        Intent intent = getIntent();
        yuejiID = intent.getStringExtra("id");

        webView.getSettings().setJavaScriptEnabled(true);

        rollPagerView.setHintView(new ColorPointHintView(this, Color.GREEN, Color.WHITE));
        rollPagerView.setPlayDelay(3000);
    }

    @Override
    protected void loadData() {
        YueJiCardDetailsRollDao.requestYueJiCardList(yuejiID, new BaseCallBack() {
            @Override
            public void success(Object data) {

                List<YueJiCardDetailsInfo.DataBean.ImagesBean> dataBean = (List<YueJiCardDetailsInfo.DataBean.ImagesBean>) data;
                YueJiCardRollAdapter adapter = new YueJiCardRollAdapter(rollPagerView, YueJiCardDetailsActivity.this, dataBean);
                rollPagerView.setAdapter(adapter);
            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(YueJiCardDetailsActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
        YueJiCardDetailsDao.requestYueJiCardList(yuejiID, new BaseCallBack() {
            @Override
            public void success(Object data) {
                YueJiCardDetailsInfo.DataBean dataBean = (YueJiCardDetailsInfo.DataBean) data;
                name.setText(dataBean.getName());
                price.setText(dataBean.getYuejika());
                address.setText(dataBean.getAddress());
                sharetv.setText(dataBean.getShare());
                keeptv.setText(dataBean.getCollection());
                tips.setText(dataBean.getPointout());
                webView.loadDataWithBaseURL(null,dataBean.getContent(),"text/html","utf-8",null);
                transportation.setText(dataBean.getTraffic());
                dialog.dismiss();
            }

            @Override
            public void failed(int errorCode, Object data) {
                dialog.dismiss();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_yue_ji_card_details;
    }
}
