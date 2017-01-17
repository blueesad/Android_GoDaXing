package com.travel.dx.godaxing.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.adapter.CommonAdapter;
import com.travel.dx.godaxing.adapter.ViewHolder;
import com.travel.dx.godaxing.dao.HotSearchItemDao;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.near.bean.CommendInfo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class HotSearchActivity extends BaseActivity implements View.OnClickListener {
    private int id;
    private ListView listView;
    private List<CommendInfo> list;
    private int page = 1;
    private View footer;
    private CommonAdapter<CommendInfo> adapter;
    private String key;
    private EditText editText;
    private Button search;
    private ImageView backbtn;
    private ProgressDialog dialog;
    private SwipeRefreshLayout refreshLayout;
    private InputMethodManager manager;

    @Override
    protected void findViews() {
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.search_refresh);
        backbtn = (ImageView) findViewById(R.id.back_search);
        search = (Button) findViewById(R.id.search_btn);
        editText = (EditText) findViewById(R.id.search_et);
        listView = (ListView) findViewById(R.id.serach_item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN)
            if (getCurrentFocus()!=null&&getCurrentFocus().getWindowToken()!=null){
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
        return super.onTouchEvent(event);
    }

    @Override
    protected void initEvent() {
        search.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // TODO 执行下拉刷新的业务
                        page = 1;
                        loadData();
                    }
                });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HotSearchActivity.this, CommonDetailsActivity.class);
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
    }

    @Override
    protected void init() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("疯狂加载中");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        Intent intent = getIntent();
        id = intent.getIntExtra("id",id)+15;
        key = intent.getStringExtra("key");
        switch (id){
            case 20:
                key = "%E9%87%87%E6%91%98";
                break;
            case 21:
                key = "%E5%9E%82%E9%92%93";
                break;
            case 22:
                key = "%E4%BD%8F%E5%AE%BF";
                break;
            case 23:
                key = "%E4%BA%B2%E5%AD%90";
                break;
            case 24:
                key = "%E5%A5%97%E9%A4%90";
                break;
            case 25:
                key = "DIY";
                break;
            case 26:
                key = "%E7%83%A7%E7%83%A4";
                break;
            case 27:
                key = "%E9%A4%90%E5%8E%85";
                break;
            case 28:
                key = "%E6%8B%93%E5%B1%95";
                break;
            case 29:
                key = "%E6%BB%91%E9%9B%AA";
                break;
            case 30:
                key = "%E6%B8%A9%E6%B3%89";
                break;
            case 31:
                key = "%E7%A7%91%E6%99%AE";
                break;
        }
        footer = HotSearchActivity.this.getLayoutInflater()
                .inflate(R.layout.layout_loading, listView, false);
        list = new ArrayList<>();
        // 使用万能适配器只需要设置item的布局和数据集，然后完成数据填充
        adapter = new CommonAdapter<CommendInfo>(HotSearchActivity.this, list,
                R.layout.layout_search) {
            @Override
            public void fillData(ViewHolder helper, int position, CommendInfo item) {
                // TODO 填充数据
                if (!item.getName().equals("null")) {
                    helper.setText(R.id.search_name, item.getName());// 给一个Item中的TextView设置数据
                }else {
                    helper.setText(R.id.search_name, "");
                }
                    if (item.getDistance()==0) {
                        helper.setText(R.id.search_distance, 0 + "km");
                    } else {
                        double distance =  ((int)item.getDistance()) / 1000.00;
                        DecimalFormat df = new DecimalFormat(".##");
                        String result = df.format(distance);
                        helper.setText(R.id.search_distance, result + "km");
                    }
                if (!item.getPrice().equals("null")) {
                    helper.setText(R.id.search_price, "￥" + item.getPrice());
                    TextView oldPrice = helper.getView(R.id.search_price_after);
                    oldPrice.getPaint().setAntiAlias(true);//抗锯齿
                    oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中间横线
                    helper.setText(R.id.search_price_after, "￥" + item.getOriginalprice());
                } else {
                    helper.setText(R.id.search_price, "免费");
                    helper.setText(R.id.search_price_after, "");
                }
                helper.setImageByUrl(R.id.search_img, item.getIv());// 加载一张网络图片
                helper.setText(R.id.search_address,item.getAddress());
            }
        };
        listView.addFooterView(footer);// 兼容4.0以前的版本
        listView.setAdapter(adapter);
        listView.removeFooterView(footer);
    }

    @Override
    protected void loadData() {
        HotSearchItemDao.requestSearchItemList(page,key, new BaseCallBack() {
            @Override
            public void success(Object data) {
                dialog.dismiss();
                refreshLayout.setRefreshing(false);// 将下拉刷新控件隐藏
                List<CommendInfo> tempList = (List<CommendInfo>) data;
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
                    Toast.makeText(HotSearchActivity.this, "数据加载完毕！", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(HotSearchActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search_item;
    }

    @Override
    public void onClick(View v) {
        key = editText.getText().toString();
        list.clear();
        adapter.notifyDataSetChanged();
        loadData();
    }
}
