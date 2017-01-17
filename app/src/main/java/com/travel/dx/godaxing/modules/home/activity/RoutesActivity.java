package com.travel.dx.godaxing.modules.home.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseActivity;
import com.travel.dx.godaxing.adapter.CommonAdapter;
import com.travel.dx.godaxing.adapter.ViewHolder;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.bean.RoutesInfo;
import com.travel.dx.godaxing.modules.home.dao.RoutesDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoutesActivity extends BaseActivity {
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page = 1;
    private List<RoutesInfo> list;
    private View footer;
    private CommonAdapter<RoutesInfo> adapter;
    private ImageView backTv;
    private ProgressDialog dialog;

    @Override
    protected void findViews() {
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.routes_swipeRefreshLayout);
        listView= (ListView) findViewById(R.id.routes_lv);
        backTv= (ImageView) findViewById(R.id.iv_fanhui_routes);

    }

    @Override
    protected void initEvent() {

        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           finish();
            }
        });
        swipeRefreshLayout
                .setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // TODO 执行下拉刷新的业务
                        page = 1;
                        loadData();
                    }
                });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(RoutesActivity.this,RoutesDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当listview滑动完全停止后开始判断是否加载下一页
                if (scrollState == SCROLL_STATE_IDLE) {
                    int lastVisiblePosition = listView.getLastVisiblePosition();// 获取当前最后一个可见的item的位置
                    // 最后一个item显示出来了
                    if (lastVisiblePosition == list.size() + listView.getHeaderViewsCount()) {
                        // 获取最后一item控件的对象
                        View childView = listView
                                .getChildAt(listView.getChildCount() - 1);
                        // 如果最后一item的底部和listview的底部重合就加载下一页
                        if (childView.getBottom() == listView.getBottom()) {
                            // TODO 加载下一页
                            page++;
                            loadData();
                        }
                    }

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
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

        footer = getLayoutInflater()
                .inflate(R.layout.layout_loading, listView, false);


        list=new ArrayList<>();
        adapter=new CommonAdapter<RoutesInfo>(this
        ,list,R.layout.adapter_home_routes_info) {
            @Override
            public void fillData(ViewHolder helper, int position, RoutesInfo item) {
                //TODO 填充数据2
               helper.setText(R.id.home_routes_title,item.getTitle());
                helper.setText(R.id.home_routes_summary,item.getSummary());
                helper.setImageByUrl(R.id.home_routes_iv,item.getImg());
                String time= timedate(item.getPublish());
                helper.setText(R.id.home_routes_publish, "2016."+time);
            }
        };
            listView.setAdapter(adapter);
    }
    private String timedate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM.dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }


    @Override
    protected void loadData() {
        RoutesDao.requstTrendsList(page, new BaseCallBack() {
            @Override
            public void success(Object data) {
                dialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);// 将下拉刷新控件隐藏
                List<RoutesInfo> tempList = (List<RoutesInfo>) data;
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
                    Toast.makeText(RoutesActivity.this, "数据加载完毕！", Toast.LENGTH_SHORT).show();
                   listView.removeFooterView(footer);
                }
                if (tempList != null) {
                    list.addAll(tempList);
                    adapter.notifyDataSetChanged();
                }
            }


            @Override
            public void failed(int errorCode, Object data) {
                dialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);// 将下拉刷新控件隐藏
                Toast.makeText(RoutesActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_routes;
    }
}
