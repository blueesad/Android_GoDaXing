package com.travel.dx.godaxing.activity;

import android.os.Bundle;
import android.view.View;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.adapter.CommonAdapter;
import com.travel.dx.godaxing.adapter.ViewHolder;
import com.travel.dx.godaxing.bean.CommonDetailsInfo;
import com.travel.dx.godaxing.widget.CommonDetails_XiangCe_GridView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class CommonDetails_XiangCeFragment extends BaseFragment {

    private CommonDetails_XiangCe_GridView gridview;
    private CommonAdapter<CommonDetailsInfo.DataBean.ImagesBean> adapter;

    @Override
    protected void findViews(View view) {
        gridview = (CommonDetails_XiangCe_GridView) view.findViewById(R.id.gridview);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            List<CommonDetailsInfo.DataBean.ImagesBean> list = (List<CommonDetailsInfo.DataBean.ImagesBean>) bundle.getSerializable("img_path");
            adapter = new CommonAdapter<CommonDetailsInfo.DataBean.ImagesBean>(getActivity(), list,
                    R.layout.adapter_xiangce_item) {
                @Override
                public void fillData(ViewHolder helper, int position, CommonDetailsInfo.DataBean.ImagesBean item) {
                    // TODO 填充数据

                    helper.setImageByUrl(R.id.imageView,item.getImg_path());

                }
            };
            gridview.setAdapter(adapter);
        }

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_xiangce;
    }
}
