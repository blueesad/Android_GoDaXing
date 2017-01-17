package com.travel.dx.godaxing.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.travel.dx.godaxing.DaXingApplication;
import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.adapter.CommonDetails_RollAdapter;
import com.travel.dx.godaxing.bean.CommonDetailsInfo;
import com.travel.dx.godaxing.dao.CommonDetails_DataDao;
import com.travel.dx.godaxing.dao.CommonDetails_GoodsDao;
import com.travel.dx.godaxing.dao.CommonDetails_RollDao;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.i.DetailsType;
import com.travel.dx.godaxing.widget.LoginDialog;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class CommonDetailsActivity extends BaseActivity {
    private RollPagerView rollPagerView;
    private ImageView ivReturn;
    private String id;
    private TextView commend_item_info;
    private TextView commend_item_price;
    private TextView price_after;
    private TextView commend_item_keep;
    private TextView commend_item_share;
    private TextView commend_item_address;
    private TextView commend_item_satnav;
    private TextView commend_item_tips;
    private CommonDetails_JieShaoFragment jieShaoFragment;
    private CommonDetails_GouMaiFragment gouMaiFragment;
    private CommonDetails_XiangCeFragment xiangCeFragment;
    private CommonDetails_DianPingFragment dianPingFragment;
    private CommonDetails_ShaiTuFragment shaiTuFragment;
    private RadioButton rb_jieshao;
    private RadioButton rb_goumai;
    private RadioButton rb_xiangce;
    private RadioButton rb_dianping;
    private RadioButton rb_shaitu;
    private RadioGroup radioGroup;
    private RelativeLayout fragment_container;
    private ProgressDialog dialog;
    private LinearLayout ll_baidumap;
    private ImageView iv_share;
    private OnekeyShare oks = new OnekeyShare();
    private int type = DetailsType.TYPE_SIGHT;
    private TextView cerate_time;
    private TextView tv_constraint;
    private TextView salecount;
    private TextView publish_time;
    private List<CommonDetailsInfo.DataBean.GoodsBean> goodsBean;
    private CommonDetailsInfo.DataBean dataBean;
    private List<CommonDetailsInfo.DataBean.ImagesBean> imagesBean;
    private int count = 0;
    private TextView tv_kefu;
    private View kefuDialogView;
    private Button btn_queding;
    private Button btn_quxiao;
    private AlertDialog kefuDialog;
    private ImageView iv_shoucang;

    @Override
    protected void findViews() {
        rollPagerView = (RollPagerView) findViewById(R.id.commend_item_view_pager);
        ivReturn = (ImageView) findViewById(R.id.iv_fanhui);
        commend_item_info = (TextView) findViewById(R.id.commend_item_info);
        commend_item_price = (TextView) findViewById(R.id.commend_item_price);
        price_after = (TextView) findViewById(R.id.price_after);
        commend_item_keep = (TextView) findViewById(R.id.commend_item_keep);
        commend_item_share = (TextView) findViewById(R.id.commend_item_share);
        commend_item_address = (TextView) findViewById(R.id.commend_item_address);
        commend_item_satnav = (TextView) findViewById(R.id.commend_item_satnav);
        commend_item_tips = (TextView) findViewById(R.id.commend_item_tips);
        ll_baidumap = (LinearLayout) findViewById(R.id.ll_baidumap);
        iv_share = (ImageView) findViewById(R.id.iv_share);
        rb_jieshao = (RadioButton) findViewById(R.id.rb_jieshao);
        rb_goumai = (RadioButton) findViewById(R.id.rb_goumai);
        rb_xiangce = (RadioButton) findViewById(R.id.rb_xiangce);
        rb_dianping = (RadioButton) findViewById(R.id.rb_dianping);
        rb_shaitu = (RadioButton) findViewById(R.id.rb_shaitu);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        fragment_container = (RelativeLayout) findViewById(R.id.fragment_container);
        cerate_time = (TextView) findViewById(R.id.cerate_time);
        tv_constraint = (TextView) findViewById(R.id.tv_constraint);
        publish_time = (TextView) findViewById(R.id.publish_time);
        salecount = (TextView) findViewById(R.id.salecount);
        tv_kefu = (TextView) findViewById(R.id.tv_kefu);
        kefuDialogView = getLayoutInflater().inflate(R.layout.layout_kefu_dialog, null);
        btn_queding = (Button) kefuDialogView.findViewById(R.id.btn_queding);
        btn_quxiao = (Button) kefuDialogView.findViewById(R.id.btn_quxiao);
        iv_shoucang = (ImageView) findViewById(R.id.iv_shoucang);
    }

    @Override
    protected void initEvent() {
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //百度地图
        ll_baidumap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommonDetailsActivity.this, BaiduMapActivity.class);
                intent.putExtra("title", commend_item_info.getText());
                startActivity(intent);
            }
        });
        //分享
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = null;
                switch (checkedId) {
                    case R.id.rb_jieshao:
                        transaction = getSupportFragmentManager().beginTransaction();
                        if (jieShaoFragment != null) {
                            transaction.replace(R.id.fragment_container, jieShaoFragment);
                        }

                        transaction.commit();
                        break;
                    case R.id.rb_goumai:
                        transaction = getSupportFragmentManager().beginTransaction();
                        if (gouMaiFragment != null) {
                            transaction.replace(R.id.fragment_container, gouMaiFragment);
                        }

                        transaction.commit();
                        break;
                    case R.id.rb_xiangce:
                        transaction = getSupportFragmentManager().beginTransaction();
                        if (xiangCeFragment != null) {
                            transaction.replace(R.id.fragment_container, xiangCeFragment);
                        }
                        transaction.commit();
                        break;
                    case R.id.rb_dianping:
                        transaction = getSupportFragmentManager().beginTransaction();
                        if (dianPingFragment != null) {
                            transaction.replace(R.id.fragment_container, dianPingFragment);
                        }
                        transaction.commit();
                        break;
                    case R.id.rb_shaitu:
                        transaction = getSupportFragmentManager().beginTransaction();
                        if (shaiTuFragment != null) {
                            transaction.replace(R.id.fragment_container, shaiTuFragment);
                        }
                        transaction.commit();
                        break;
                }
            }
        });

        tv_kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kefuDialog.show();
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
        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kefuDialog.dismiss();

            }
        });

        //收藏
        iv_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DaXingApplication.isLogin){
                    Toast.makeText(CommonDetailsActivity.this, "收藏成功",Toast.LENGTH_SHORT).show();
                    iv_shoucang.setImageResource(R.mipmap.star_press);
                }else {
                    LoginDialog.getInstance(CommonDetailsActivity.this).show();
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
        oks.setTitle(commend_item_info.getText().toString());
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

        //联系客服对话框
        kefuDialog = new AlertDialog.Builder(this)
                .setView(kefuDialogView)
                .setCancelable(false)
                .create();

        //滚动广告
        rollPagerView.setHintView(new ColorPointHintView(this, Color.GREEN, Color.WHITE));
        rollPagerView.setPlayDelay(3000);

    }

    @Override
    protected void loadData() {
        CommonDetails_RollDao.requestShopDetailsList(type, id, new BaseCallBack() {
            @Override
            public void success(Object data) {
                count++;
                Log.e("------", "Images: " + count);
                imagesBean = (List<CommonDetailsInfo.DataBean.ImagesBean>) data;
                CommonDetails_RollAdapter adapter = new CommonDetails_RollAdapter(rollPagerView, CommonDetailsActivity.this, imagesBean);
                rollPagerView.setAdapter(adapter);
                if (imagesBean.size()!=0){
                    oks.setImageUrl(imagesBean.get(0).getImg_path());
                }
                if (count == 3) {
                    dialog.dismiss();
                    addFragment(imagesBean, dataBean, goodsBean);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                dialog.dismiss();
                Toast.makeText(CommonDetailsActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });

        CommonDetails_DataDao.requestShopDetailsDataList(type, id, new BaseCallBack() {
            @Override
            public void success(Object data) {
                count++;
                Log.e("------", "Deta: " + count);
                dataBean = (CommonDetailsInfo.DataBean) data;

                if (type == DetailsType.TYPE_SIGHT) {
                    commend_item_info.setText(dataBean.getName());
                    commend_item_price.setText("¥ " + dataBean.getPrice());
                    price_after.getPaint().setAntiAlias(true);//抗锯齿
                    price_after.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中间横线
                    price_after.setText("¥ " + dataBean.getOriginalprice());
                    commend_item_keep.setText(dataBean.getCollection());
                    commend_item_share.setText(dataBean.getShare());
                    commend_item_address.setText(dataBean.getAddress());
                    commend_item_tips.setText(dataBean.getPointout());
                } else if (type == DetailsType.TYPE_PLAY) {
                    commend_item_info.setText(dataBean.getName());
                    salecount.setText(dataBean.getSalecount());
                    commend_item_keep.setText(dataBean.getCollection());
                    commend_item_share.setText(dataBean.getShare());
                    commend_item_address.setText(dataBean.getAddress());
                    commend_item_tips.setText(dataBean.getPointout());

                }
                if (count == 3) {
                    dialog.dismiss();
                    addFragment(imagesBean, dataBean, goodsBean);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                dialog.dismiss();
                Toast.makeText(CommonDetailsActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
        CommonDetails_GoodsDao.requestShopDetailsList(type, id, new BaseCallBack() {
                    @Override
                    public void success(Object data) {
                        count++;
                        Log.e("------", "Goods: " + count);
                goodsBean = (List<CommonDetailsInfo.DataBean.GoodsBean>) data;
                if (type == DetailsType.TYPE_PLAY) {
                    cerate_time.setText("报名开始时间： " + timedate(Integer.parseInt(goodsBean.get(0).getS_time())));
                    publish_time.setText(" 活动时间： " + timedate(Integer.parseInt(goodsBean.get(0).getE_time())));
                    tv_constraint.setText("人数限定： " + goodsBean.get(0).getStock());
                }
                if (count == 3) {
                    dialog.dismiss();
                    addFragment(imagesBean, dataBean, goodsBean);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(CommonDetailsActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private String timedate(int time) {
        Date data = new Date(time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String times = sdf.format(data);
        return times;
    }

    private void addFragment(List<CommonDetailsInfo.DataBean.ImagesBean> imagesBean, CommonDetailsInfo.DataBean dataBean, List<CommonDetailsInfo.DataBean.GoodsBean> goodsBean) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        xiangCeFragment = new CommonDetails_XiangCeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("img_path", (Serializable) imagesBean);
        xiangCeFragment.setArguments(bundle);

        jieShaoFragment = new CommonDetails_JieShaoFragment();
        bundle.putString("traffic", dataBean.getTraffic());
        bundle.putString("content", dataBean.getContent());
        bundle.putInt("type", type);
        bundle.putSerializable("goods", (Serializable) goodsBean);
        jieShaoFragment.setArguments(bundle);

        gouMaiFragment = new CommonDetails_GouMaiFragment();

        bundle.putString("notice", dataBean.getNotice());
        gouMaiFragment.setArguments(bundle);

        dianPingFragment = new CommonDetails_DianPingFragment();
        shaiTuFragment = new CommonDetails_ShaiTuFragment();
        transaction.add(R.id.fragment_container, jieShaoFragment);
        transaction.commit();

    }

    @Override
    protected int setLayoutId() {
        int layoutId = R.layout.activity_common_details_stght;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0) + "";
        type = intent.getIntExtra("type", DetailsType.TYPE_SIGHT);

        if (type == DetailsType.TYPE_SIGHT) {
            layoutId = R.layout.activity_common_details_stght;
        } else if (type == DetailsType.TYPE_PLAY) {
            layoutId = R.layout.activity_common_details_play;
        }


        return layoutId;
    }


}













