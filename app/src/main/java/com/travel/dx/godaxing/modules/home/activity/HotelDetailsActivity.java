package com.travel.dx.godaxing.modules.home.activity;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaiduMapActivity;
import com.travel.dx.godaxing.activity.BaseActivity;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.adapter.HotelRollAdapter;
import com.travel.dx.godaxing.modules.home.bean.HotelDetailsInfo;
import com.travel.dx.godaxing.modules.home.dao.HotelDetailsDao;
import com.travel.dx.godaxing.modules.home.dao.HotelDetailsRullDao;

import java.util.List;

public class HotelDetailsActivity extends BaseActivity {
    private String HotelId;
    private ImageView backIv;
    private RollPagerView rollPagerView;
    private TextView nameTv;
    private  TextView addressTv;
    private  TextView phoneTv;
    private TextView contentTv;
    private ProgressDialog dialog;
    private TextView trafficTv;
    private RelativeLayout jianjie_rl;
    private LinearLayout jianjieLl;
    private RelativeLayout phone_start;
    private AlertDialog dingfangDialog;
    private View dingfangDialogView;
    private Button btn_queding;
    private Button btn_quxiao;
    private TextView hotelDetails_keep;
    private TextView hotelDetails_share;
    private HotelDetailsInfo.DataBean dataBean;
    private LinearLayout baidumap_ll;


    @Override
    protected void findViews() {
        backIv= (ImageView) findViewById(R.id.iv_fanhui_hotelDetails);
        rollPagerView= (RollPagerView) findViewById(R.id.hotelDetails_pagerView);
        nameTv= (TextView) findViewById(R.id.hotelDetails_name);
        addressTv= (TextView) findViewById(R.id.hotelDetails_address);
        phoneTv= (TextView) findViewById(R.id.hotelDetails_phone);
        contentTv= (TextView) findViewById(R.id.hotelDetails_jianjie);
        trafficTv= (TextView) findViewById(R.id.hotelDetails_jiaotong);
        jianjie_rl= (RelativeLayout) findViewById(R.id.jianjie_tese);
        jianjieLl= (LinearLayout) findViewById(R.id.jianjie_linearlayout);
        phone_start= (RelativeLayout) findViewById(R.id.phone_rl);
        hotelDetails_keep= (TextView) findViewById(R.id.hotelDetails_keep);
        hotelDetails_share= (TextView) findViewById(R.id.hotelDetails_share);
        dingfangDialogView = getLayoutInflater().inflate(R.layout.layout_dingfang_dialog, null);
        btn_queding = (Button) dingfangDialogView.findViewById(R.id.btn_queding_dingfang);
        btn_quxiao = (Button) dingfangDialogView.findViewById(R.id.btn_quxiao_dingfang);
        baidumap_ll= (LinearLayout) findViewById(R.id.baidumap_ll);
    }

    @Override
    protected void initEvent() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        jianjie_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jianjieLl.getVisibility()==View.VISIBLE){
                    jianjieLl.setVisibility(View.GONE);
                    jianjie_rl.getChildAt(1).setBackgroundResource(R.mipmap.yincang);
                }
                else{
                    jianjieLl.setVisibility(View.VISIBLE);
                    jianjie_rl.getChildAt(1).setBackgroundResource(R.mipmap.zhankai);
                }
            }
        });
        phone_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dingfangDialog.show();
            }
        });
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri teluri = Uri.parse("tel:"+dataBean.getPhone());
                intent.setData(teluri);
                startActivity(intent);
            }
        });
        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dingfangDialog.dismiss();

            }
        });
        baidumap_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelDetailsActivity.this, BaiduMapActivity.class);
                intent.putExtra("title",dataBean.getName());
                startActivity(intent);
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

        dingfangDialog = new AlertDialog.Builder(this)
                .setView(dingfangDialogView)
                .setCancelable(false)
                .create();

        Intent intent =getIntent();
        HotelId=intent.getStringExtra("id");
        Log.e("**", "ID"+HotelId);
        rollPagerView.setHintView(new ColorPointHintView(this, Color.GREEN, Color.WHITE));
        rollPagerView.setPlayDelay(3000);
    }

    @Override
    protected void loadData() {
        HotelDetailsRullDao.requestCommendItemList(HotelId, new BaseCallBack() {
            @Override
            public void success(Object data) {
                final List<HotelDetailsInfo.DataBean.ImagesBean> dataBean = (List<HotelDetailsInfo.DataBean.ImagesBean>) data;
                HotelRollAdapter adapter = new HotelRollAdapter(rollPagerView, HotelDetailsActivity.this, dataBean);
                rollPagerView.setAdapter(adapter);
            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(HotelDetailsActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });

        HotelDetailsDao.requestCommendItemList(HotelId, new BaseCallBack() {
            @Override
            public void success(Object data) {
                dialog.dismiss();
                dataBean = (HotelDetailsInfo.DataBean)data;
                nameTv.setText(dataBean.getName());
                addressTv.setText(dataBean.getAddress());
                phoneTv.setText(dataBean.getPhone());
                contentTv.setText(dataBean.getContent());
                trafficTv.setText(dataBean.getTraffic());
                hotelDetails_share.setText(dataBean.getShare());
                hotelDetails_keep.setText(dataBean.getCollection());
            }

            @Override
            public void failed(int errorCode, Object data) {
                dialog.dismiss();
            }
        });
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_hotel_details;
    }
}
