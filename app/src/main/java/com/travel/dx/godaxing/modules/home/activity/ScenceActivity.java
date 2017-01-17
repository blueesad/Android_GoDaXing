package com.travel.dx.godaxing.modules.home.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseActivity;
import com.travel.dx.godaxing.activity.CommonDetailsActivity;
import com.travel.dx.godaxing.activity.SearchActivity;
import com.travel.dx.godaxing.adapter.CommonAdapter;
import com.travel.dx.godaxing.adapter.ViewHolder;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.bean.ScenceInfo;
import com.travel.dx.godaxing.modules.home.dao.ScenceDao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ScenceActivity extends BaseActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private int page = 1;
    private List<ScenceInfo> list;
    private View footer;
    private CommonAdapter<ScenceInfo> adapter;
    private ProgressDialog dialog;
    private ImageView backIv;
    private TextView search;
    private LinearLayout linearLayout;

    @Override
    protected void findViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.scence_swipeRefreshLayout);
        listView = (ListView) findViewById(R.id.scence_lv);
        backIv = (ImageView) findViewById(R.id.iv_fanhui_scence);
        search = (TextView) findViewById(R.id.search);
        linearLayout = (LinearLayout) findViewById(R.id.search_ll);
    }

    @Override
    protected void initEvent() {
        backIv.setOnClickListener(new View.OnClickListener() {
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
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(ScenceActivity.this, CommonDetailsActivity.class);
                intent.putExtra("id",Integer.parseInt(list.get(position).getId()));
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
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationSet animationSet = new AnimationSet(true);
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, ScaleAnimation.RELATIVE_TO_PARENT, 0.5f, ScaleAnimation.RELATIVE_TO_PARENT, 0.5f);
                scaleAnimation.setDuration(1000);
                animationSet.addAnimation(scaleAnimation);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(1000);
                animationSet.addAnimation(alphaAnimation);
                linearLayout.startAnimation(animationSet);
                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(ScenceActivity.this, SearchActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
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

        footer = ScenceActivity.this.getLayoutInflater()
                .inflate(R.layout.layout_loading, listView, false);
        list = new ArrayList<>();
        adapter = new CommonAdapter<ScenceInfo>(ScenceActivity.this, list,
                R.layout.adapter_home_scence_info) {
            @Override
            public void fillData(ViewHolder helper, int position, ScenceInfo item) {
                helper.setText(R.id.scence_name, item.getName());// 给一个Item中的TextView设置数据
                double distance = item.getDistance() / 1000.00;
                DecimalFormat df = new DecimalFormat(".##");
                String result = df.format(distance);
                helper.setText(R.id.scence_goods, item.getGoodsdesc());
                helper.setText(R.id.scence_distance, result + "km");
                if (!item.getPrice().equals("null")) {
                    helper.setText(R.id.scence_price, "￥" + item.getPrice());
                    TextView oldPrice = helper.getView(R.id.scence_price_after);
                    oldPrice.getPaint().setAntiAlias(true);//抗锯齿
                    oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中间横线
                    helper.setText(R.id.scence_price_after, "￥" + item.getOriginalprice());
                } else {
                    helper.setText(R.id.scence_price, "免费");
                    helper.setText(R.id.scence_price_after, "");
                }

                helper.setImageByUrl(R.id.scence_iv, item.getImg());// 加载一张网络图片

            }
        };
        listView.addFooterView(footer);// 兼容4.0以前的版本
        listView.setAdapter(adapter);
        listView.removeFooterView(footer);
    }

    @Override
    protected void loadData() {
        // TODO 请求网络数据，解析后进行界面更新
        ScenceDao.requestScenceList(page, new BaseCallBack() {
            @Override
            public void success(Object data) {
                dialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);// 将下拉刷新控件隐藏
                List<ScenceInfo> tempList = (List<ScenceInfo>) data;
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
                    Toast.makeText(ScenceActivity.this, "数据加载完毕！", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ScenceActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_scence;
    }
}
