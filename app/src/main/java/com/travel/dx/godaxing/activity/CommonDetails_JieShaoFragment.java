package com.travel.dx.godaxing.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.adapter.CommonAdapter;
import com.travel.dx.godaxing.adapter.ViewHolder;
import com.travel.dx.godaxing.bean.CommonDetailsInfo;
import com.travel.dx.godaxing.i.DetailsType;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class CommonDetails_JieShaoFragment extends BaseFragment{

    private RelativeLayout rl_jiaotong;
    private RelativeLayout rl_tese;
    private TextView commend_item_transportation;
    private WebView commend_item_webview;
    private ListView jieshao_listview;
    private CommonAdapter<CommonDetailsInfo.DataBean.GoodsBean> adapter;
    private int type;

    @Override
    protected void findViews(View view) {
        rl_jiaotong = (RelativeLayout) view.findViewById(R.id.rl_jiaotong);
        rl_tese = (RelativeLayout) view.findViewById(R.id.rl_tese);
        commend_item_transportation = (TextView) view.findViewById(R.id.commend_item_transportation);
        commend_item_webview = (WebView) view.findViewById(R.id.commend_item_webview);
        jieshao_listview = (ListView) view.findViewById(R.id.jieshao_listview);
    }

    @Override
    protected void initEvent() {
        rl_jiaotong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commend_item_transportation.getVisibility() == View.VISIBLE) {
                    commend_item_transportation.setVisibility(View.GONE);
                    rl_jiaotong.getChildAt(1).setBackgroundResource(R.mipmap.yincang);
                } else {
                    commend_item_transportation.setVisibility(View.VISIBLE);
                    rl_jiaotong.getChildAt(1).setBackgroundResource(R.mipmap.zhankai);
                }
            }
        });
        rl_tese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commend_item_webview.getVisibility() == View.VISIBLE) {
                    commend_item_webview.setVisibility(View.GONE);
                    rl_tese.getChildAt(1).setBackgroundResource(R.mipmap.yincang);
                } else {
                    commend_item_webview.setVisibility(View.VISIBLE);
                    rl_tese.getChildAt(1).setBackgroundResource(R.mipmap.zhankai);
                }
            }
        });
    }

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        type = bundle.getInt("type");
        if (type == DetailsType.TYPE_SIGHT) {
            rl_jiaotong.setVisibility(View.VISIBLE);
            rl_tese.setVisibility(View.VISIBLE);
        } else if (type == DetailsType.TYPE_PLAY) {
            rl_jiaotong.setVisibility(View.GONE);
            rl_tese.setVisibility(View.GONE);
        }
        commend_item_transportation.setText(bundle.getString("traffic"));

        commend_item_webview.getSettings().setJavaScriptEnabled(true);
        commend_item_webview.loadDataWithBaseURL(null,bundle.getString("content"), "text/html", "utf-8", null);
        List<CommonDetailsInfo.DataBean.GoodsBean> list = (List<CommonDetailsInfo.DataBean.GoodsBean>) bundle.getSerializable("goods");
        adapter = new CommonAdapter<CommonDetailsInfo.DataBean.GoodsBean>(getActivity(),list,R.layout.adapter_goods_info) {
            @Override
            public void fillData(ViewHolder helper, int position, CommonDetailsInfo.DataBean.GoodsBean item) {
                //填充数据
                helper.setText(R.id.tv_title, item.getName());// 给一个Item中的TextView设置数据
            helper.setText(R.id.tv_price,"¥ "+item.getPrice()+"");
            TextView oldPrice = helper.getView(R.id.tv_oldprice);
            oldPrice.getPaint().setAntiAlias(true);//抗锯齿
            oldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG ); //中间横线
            helper.setText(R.id.tv_oldprice,"¥ "+item.getOriginalprice()+"");
            helper.setText(R.id.tv_sold,"  已售："+item.getSold());
        }
        };
        jieshao_listview.setAdapter(adapter);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_jieshao;
    }
}
