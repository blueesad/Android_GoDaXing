package com.travel.dx.godaxing.modules.home.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseFragment;
import com.travel.dx.godaxing.activity.CommonDetailsActivity;
import com.travel.dx.godaxing.adapter.CommonAdapter;
import com.travel.dx.godaxing.adapter.ViewHolder;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.bean.HomeDetailsInfo;
import com.travel.dx.godaxing.modules.home.dao.HomeCommendDao;
import com.travel.dx.godaxing.widget.ScrollListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class CommendHomeFragment extends BaseFragment {
    private ScrollListView listView;
    private int page = 1;
    private List<HomeDetailsInfo.DataBean> list;
    private CommonAdapter<HomeDetailsInfo.DataBean> adapter;
    private View footer;
    private boolean isloadData = true;


    @Override
    protected void findViews(View view) {
        listView = (ScrollListView) view.findViewById(R.id.home_commend_lv);
        footer = getActivity().getLayoutInflater().inflate(R.layout.layout_loading, listView, false);
    }

    @Override
    protected void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getActivity(), CommonDetailsActivity.class);
                intent.putExtra("id",Integer.parseInt(list.get(position).getId()));
                startActivity(intent);

            }
        });
    }
    @Subscribe
    public void UpdatePage(HomeDetailsInfo info) {
        if(isloadData){
            page++;
            loadData();
        }
    }

    @Override
    protected void init() {
        page = 1;
        EventBus.getDefault().register(this);//注册eventBus
        list = new ArrayList<>();
        // 使用万能适配器只需要设置item的布局和数据集，然后完成数据填充
        adapter = new CommonAdapter<HomeDetailsInfo.DataBean>(getActivity(), list,
                R.layout.adapter_home_commend_info) {
            @Override
            public void fillData(ViewHolder helper, int position, HomeDetailsInfo.DataBean item) {
                // TODO 填充数据
                helper.setText(R.id.home_name,item.getName());// 给一个Item中的TextView设置数据
                double distance =  item.getDistance() / 1000.00;
                DecimalFormat df=new DecimalFormat(".##");
                String result=df.format(distance);
                helper.setText(R.id.home_distance, result + "km");
                if (!item.getPrice().equals("null")) {
                    helper.setText(R.id.home_price, "￥" + item.getPrice());
                    helper.setText(R.id.home_goods,item.getGoodsdesc());
                    TextView oldPrice = helper.getView(R.id.home_price_after);
                    oldPrice.getPaint().setAntiAlias(true);//抗锯齿
                    oldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG ); //中间横线
                    helper.setText(R.id.home_price_after, "￥" +item.getOriginalprice());
                } else {
                    helper.setText(R.id.home_price, "免费");
                    helper.setText(R.id.home_goods,item.getGoodsdesc());
                    helper.setText(R.id.home_price_after,"");
                }

                helper.setImageByUrl(R.id.home_iv, item.getImg());// 加载一张网络图片

            }
        };
        listView.addFooterView(footer);// 兼容4.0以前的版本
        listView.setAdapter(adapter);
        listView.removeFooterView(footer);
    }

    @Override
    protected void loadData() {
        // TODO 请求网络数据，解析后进行界面更新
        HomeCommendDao.requestCommendList(page, new BaseCallBack() {
            @Override
            public void success(Object data) {
                List<HomeDetailsInfo.DataBean> tempList = (List<HomeDetailsInfo.DataBean>) data;
                // 只有当page等于初始化值的时候才需要清除list的数据
                if (page == 1) {
                    list.clear();
                }
                // 仅当listview中不存在footer的时候才可以添加footer
                if (listView.getFooterViewsCount() == 0) {
                    listView.addFooterView(footer);
                }
                // 当当前页的数据返回小于10时，就代表已经是最后一页
                if (tempList.size() < 10) {
                    Toast.makeText(getActivity(), "已经没有更多数据了", Toast.LENGTH_SHORT)
                            .show();
                    listView.removeFooterView(footer);
                    isloadData = false;
                }

                if (tempList != null) {
                    list.addAll(tempList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(getActivity(), "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected int setLayoutId() {
        return R.layout.layout_home_commend;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
