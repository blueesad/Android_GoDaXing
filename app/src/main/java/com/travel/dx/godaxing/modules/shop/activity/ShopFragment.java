package com.travel.dx.godaxing.modules.shop.activity;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseFragment;
import com.travel.dx.godaxing.activity.CommonDetailsActivity;
import com.travel.dx.godaxing.activity.SearchActivity;
import com.travel.dx.godaxing.adapter.CommonAdapter;
import com.travel.dx.godaxing.adapter.ViewHolder;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.shop.bean.ShopInfo;
import com.travel.dx.godaxing.modules.shop.dao.ShopDao;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends BaseFragment {
    private SwipeRefreshLayout refreshLayout;
    private ListView listView;
    private List<ShopInfo> list;
    private CommonAdapter adapter;
    private int page;
    private View footer;
    private TextView search;
    private LinearLayout linearLayout;

    @Override
    protected void findViews(View view) {
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        linearLayout = (LinearLayout) view.findViewById(R.id.search_ll);
        listView = (ListView) view.findViewById(R.id.shop_lv);
        search = (TextView) view.findViewById(R.id.search);
    }

    @Override
    protected void initEvent() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    public void onAnimationStart(Animation animation) {}
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(getActivity(),SearchActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });

            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO 执行下拉刷新
                page = 1;
                loadData();

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                Intent intent = new Intent(getContext(),CommonDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
//                intent.putExtra("type",0);
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {
                //当listview滑动完全停止后开始判断是否加载下一页
                if(scrollState==SCROLL_STATE_IDLE){
                    int lastVisiblePosition=listView.getLastVisiblePosition();//获取当前最后一个可见的item的位置
                    //最后一个item显示出来了
                    if(lastVisiblePosition==list.size()+listView.getHeaderViewsCount()){
                        //获取最后一item控件的对象
                        View childView=listView.getChildAt(listView.getChildCount()-1);
                        //如果最后一item的底部和listview的底部重合就加载下一页
                        if(childView.getBottom()==listView.getBottom()){
                            //TODO 加载下一页
                            page++;
                            loadData();
                        }
                    }

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void init() {

        footer = getActivity().getLayoutInflater()
                .inflate(R.layout.layout_loading, listView, false);
        list = new ArrayList<>();
        adapter = new CommonAdapter<ShopInfo>(getActivity(),list,R.layout.adapter_shop_info) {
            @Override
            public void fillData(ViewHolder helper, int position, ShopInfo item) {
                //填充数据
                helper.setText(R.id.tv_title, item.getName());// 给一个Item中的TextView设置数据
                helper.setText(R.id.tv_price,"¥ "+item.getPrice()+"");
                TextView oldPrice = helper.getView(R.id.tv_oldprice);
                oldPrice.getPaint().setAntiAlias(true);//抗锯齿
                oldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG ); //中间横线
                helper.setText(R.id.tv_oldprice,"¥ "+item.getOldprice()+"");
                helper.setImageByUrl(R.id.iv_img,item.getImg());
            }
        };

        listView.addFooterView(footer);// 兼容4.0以前的版本
        listView.setAdapter(adapter);
        listView.removeFooterView(footer);
    }

    @Override
    protected void loadData() {
        ShopDao.requestGameList(page, new BaseCallBack()
        {
            @Override
            public void success(Object data)
            {
                refreshLayout.setRefreshing(false);// 将下拉刷新控件隐藏
                List<ShopInfo> tempList = (List<ShopInfo>) data;
                //只有当page等于初始化值的时候才需要清除list的数据
                if(page==1){
                    list.clear();
                }
                if (listView.getFooterViewsCount() == 0)
                {
                    listView.addFooterView(footer);
                }
                // 当当前页的数据返回小于10时，就代表已经是最后一页
                if (tempList.size() < 10)
                {
                    Toast.makeText(getActivity(),"数据加载完毕！",Toast.LENGTH_SHORT).show();
                    listView.removeFooterView(footer);
                }
                if (tempList != null)
                {
                    list.addAll(tempList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(int errorCode, Object data)
            {
                refreshLayout.setRefreshing(false);// 将下拉刷新控件隐藏
                Toast.makeText(getActivity(), "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shop_layout;
    }
}
