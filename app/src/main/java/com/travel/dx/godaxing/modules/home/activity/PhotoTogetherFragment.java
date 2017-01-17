package com.travel.dx.godaxing.modules.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseFragment;
import com.travel.dx.godaxing.adapter.CommonAdapter;
import com.travel.dx.godaxing.adapter.ViewHolder;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.bean.PhotoInfo;
import com.travel.dx.godaxing.modules.home.dao.PhotoTogetherDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class PhotoTogetherFragment extends BaseFragment {
    private List<PhotoInfo.DataBean> list;
    private ListView listView;
    private int page = 1;
    private CommonAdapter<PhotoInfo.DataBean> adapter;
    private View footer;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void findViews(View view) {
        listView = (ListView) view.findViewById(R.id.photo_listview);
        footer = getActivity().getLayoutInflater().inflate(R.layout.layout_loading, listView, false);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
    }

    @Override
    protected void initEvent() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO 执行下拉刷新
                page = 1;
                loadData();

            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    int lastVisiblePosition = listView.getLastVisiblePosition();// 获取当前最后一个可见的item的位置
                    // 最后一个item显示出来了
                    if (lastVisiblePosition == (list.size() + 1) / 2
                            + listView.getHeaderViewsCount()) {
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
        page = 1;
        list = new ArrayList<>();
        adapter = new CommonAdapter<PhotoInfo.DataBean>(getActivity(), list,
                R.layout.adapter_photo_info) {
            @Override
            public void fillData(ViewHolder helper, int position, PhotoInfo.DataBean item) {
// TODO 填充数据(positoin为行数)
                final PhotoInfo.DataBean leftInfo = list.get(position * 2);
                View leftView = helper.getView(R.id.left_photo_rl);
                leftView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),PhotoDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("photoInfo",leftInfo);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                helper.setImageByUrl(R.id.left_photo_img, leftInfo.getImages().get(0).getImage_path());
                helper.setText(R.id.left_photo_name, leftInfo.getTitle());
                View rightView = helper.getView(R.id.right_photo_rl);
                if (position * 2 + 1 == list.size()) {
                    rightView.setVisibility(View.INVISIBLE);// 如果使用GONE可能使左边的item占满一行
                } else {
                    rightView.setVisibility(View.VISIBLE);
                    final PhotoInfo.DataBean rightInfo = list.get(position * 2 + 1);

                    rightView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),PhotoDetailsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("photoInfo",rightInfo);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    helper.setImageByUrl(R.id.right_photo_img, rightInfo.getImages().get(0).getImage_path());
                    helper.setText(R.id.right_photo_name, rightInfo.getTitle());
                }
            }
        };
        listView.addFooterView(footer);// 兼容4.0以前的版本
        listView.setAdapter(adapter);
        listView.removeFooterView(footer);


    }


    @Override
    protected void loadData() {
        PhotoTogetherDao.requestPhotoList(page, new BaseCallBack() {
            @Override
            public void success(Object data) {
                // refreshLayout.setRefreshing(false);// 将下拉刷新控件隐藏
                List<PhotoInfo.DataBean> tempList = (List<PhotoInfo.DataBean>) data;
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
                }
                if (tempList != null) {
                    list.addAll(tempList);
                    // 由于当前需求要求一行显示两条数据，所在得到数据集后需要重新设置行数
                    adapter.setCount((list.size() + 1) / 2);
                    adapter.notifyDataSetChanged();
                }
                refreshLayout.setRefreshing(false);// 将下拉刷新控件隐藏
            }

            @Override
            public void failed(int errorCode, Object data) {
                refreshLayout.setRefreshing(false);// 将下拉刷新控件隐藏
                Toast.makeText(getActivity(), "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.layout_photo_commend;
    }

}
