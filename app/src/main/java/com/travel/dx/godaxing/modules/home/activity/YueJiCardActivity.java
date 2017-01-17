package com.travel.dx.godaxing.modules.home.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseActivity;
import com.travel.dx.godaxing.adapter.CommonAdapter;
import com.travel.dx.godaxing.adapter.ViewHolder;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.adapter.HotelRollAdapter;
import com.travel.dx.godaxing.modules.home.bean.HotelDetailsInfo;
import com.travel.dx.godaxing.modules.home.bean.YueJiCardInfo;
import com.travel.dx.godaxing.modules.home.dao.YueJiCardDao;
import com.travel.dx.godaxing.modules.home.dao.YueJiCardRollDao;
import com.travel.dx.godaxing.modules.home.dao.YueJiCardSightDao;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YueJiCardActivity extends BaseActivity {


    private RollPagerView rollPagerView;
    private ImageView backbtn;
    private TextView share;

    private ProgressDialog dialog;
    private int count = 0;
    private List<HotelDetailsInfo.DataBean.ImagesBean> imagesBean;
    private TextView time;
    private TextView shoucang;
    private RelativeLayout tuijian;
    private RelativeLayout together;
    private TextView togetherDetails;
    private ListView listView;
    private WebView webView;
    private TextView title;
    private CommonAdapter<YueJiCardInfo.DataBean.SightBean> adapter;
    private List<YueJiCardInfo.DataBean.SightBean> list;
    private LinearLayout linearLayout;
    private Intent intent;

    @Override
    protected void findViews() {
        rollPagerView = (RollPagerView) findViewById(R.id.yue_ji_roll_pager);
        backbtn = (ImageView) findViewById(R.id.xiangqing_fanhui);
        share = (TextView) findViewById(R.id.yue_ji_share);
        time = (TextView) findViewById(R.id.yue_ji_card_time);
        shoucang = (TextView) findViewById(R.id.yue_ji_shoucang);
        tuijian = (RelativeLayout) findViewById(R.id.yue_ji_tuijian);
        together = (RelativeLayout) findViewById(R.id.yue_ji_together);
        togetherDetails = (TextView) findViewById(R.id.yue_ji_together_details);
        listView = (ListView) findViewById(R.id.yue_ji_list_view);
        webView = (WebView) findViewById(R.id.yue_ji_web_view);
        title = (TextView) findViewById(R.id.YueJi_title);
        linearLayout = (LinearLayout) findViewById(R.id.yue_ji_linearlayout);
    }

    @Override
    protected void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(YueJiCardActivity.this,YueJiCardDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                AnimationSet animationSet = new AnimationSet(true);
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f,2.0f,1.0f,2.0f,ScaleAnimation.RELATIVE_TO_PARENT,0.5f,ScaleAnimation.RELATIVE_TO_PARENT,0.5f);
                scaleAnimation.setDuration(1000);
                animationSet.addAnimation(scaleAnimation);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
                alphaAnimation.setDuration(1000);
                animationSet.addAnimation(alphaAnimation);
                linearLayout.startAnimation(animationSet);

                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });
        tuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView.getVisibility() == View.VISIBLE) {
                    listView.setVisibility(View.GONE);
                    tuijian.getChildAt(1).setBackgroundResource(R.mipmap.yincang);
                } else {
                    listView.setVisibility(View.VISIBLE);
                    tuijian.getChildAt(1).setBackgroundResource(R.mipmap.zhankai);
                }
            }
        });
        together.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (togetherDetails.getVisibility() == View.VISIBLE) {
                    togetherDetails.setVisibility(View.GONE);
                    together.getChildAt(1).setBackgroundResource(R.mipmap.yincang);
                } else {
                    togetherDetails.setVisibility(View.VISIBLE);
                    together.getChildAt(1).setBackgroundResource(R.mipmap.zhankai);
                }
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list = new ArrayList<>();
        adapter = new CommonAdapter<YueJiCardInfo.DataBean.SightBean>(YueJiCardActivity.this, list,
                R.layout.adapter_home_commend_info) {
            @Override
            public void fillData(ViewHolder helper, int position, YueJiCardInfo.DataBean.SightBean item) {
                // TODO 填充数据
                helper.setText(R.id.home_name,item.getName());// 给一个Item中的TextView设置数据
                double distance =  item.getDistance() / 1000.00;
                DecimalFormat df=new DecimalFormat(".##");
                String result=df.format(distance);
                helper.setText(R.id.home_distance, result + "km");
                helper.setText(R.id.home_price,item.getYuejika());
                helper.setText(R.id.home_goods,"");
                helper.setText(R.id.home_price_after,"");
                helper.setImageByUrl(R.id.home_iv, item.getImg());// 加载一张网络图片

            }
        };
        listView.setAdapter(adapter);
    }



    @Override
    protected void init() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("疯狂加载中");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        //webview设置
        webView.getSettings().setJavaScriptEnabled(true);

        //滚动广告
        rollPagerView.setHintView(new ColorPointHintView(this, Color.GREEN, Color.WHITE));
        rollPagerView.setPlayDelay(3000);
    }

    @Override
    protected void loadData() {
        YueJiCardRollDao.requestCommendItemList(new BaseCallBack() {
            @Override
            public void success(Object data) {
                count++;
                Log.e("------", "Images: " + count);
                imagesBean = (List<HotelDetailsInfo.DataBean.ImagesBean>) data;
                HotelRollAdapter adapter = new HotelRollAdapter(rollPagerView, YueJiCardActivity.this, imagesBean);
                rollPagerView.setAdapter(adapter);
            }
            @Override
            public void failed(int errorCode, Object data) {

            }
        });
        YueJiCardDao.requestYueJiCardList(new BaseCallBack() {
            @Override
            public void success(Object data) {
                YueJiCardInfo.DataBean dataBean = (YueJiCardInfo.DataBean) data;
                title.setText(dataBean.getTitle());
                time.setText(timedata(dataBean.getLast_modify_time()));
                share.setText(dataBean.getShare());
                shoucang.setText(dataBean.getCollection());
                webView.loadDataWithBaseURL(null,dataBean.getContent(),"text/html","utf-8",null);
                dialog.dismiss();
            }
            @Override
            public void failed(int errorCode, Object data) {
                dialog.dismiss();
                Toast.makeText(YueJiCardActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
        YueJiCardSightDao.requestYueJiCardSightList(new BaseCallBack() {
            @Override
            public void success(Object data) {
                List<YueJiCardInfo.DataBean.SightBean> tempList = (List<YueJiCardInfo.DataBean.SightBean>) data;
                adapter.notifyDataSetChanged();
                if (tempList != null) {
                    list.addAll(tempList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(YueJiCardActivity.this, data.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String timedata(int time) {
        Date data = new Date(time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String times = sdf.format(data);
        return times;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_yue_ji_card;
    }
}
