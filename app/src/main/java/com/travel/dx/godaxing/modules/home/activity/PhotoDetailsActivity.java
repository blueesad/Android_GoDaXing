package com.travel.dx.godaxing.modules.home.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseActivity;
import com.travel.dx.godaxing.adapter.CommonAdapter;
import com.travel.dx.godaxing.adapter.ViewHolder;
import com.travel.dx.godaxing.modules.home.bean.PhotoInfo;
import com.travel.dx.godaxing.util.BitmapUtil;
import com.travel.dx.godaxing.widget.CommonDetails_XiangCe_GridView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class PhotoDetailsActivity extends BaseActivity {
    private ImageView backTv;
    private CommonDetails_XiangCe_GridView gridview;
    private TextView tv_name;
    private TextView tv_address;
    private TextView tv_time;
    private ImageView iv_touxiang;
    private CommonAdapter<PhotoInfo.DataBean.ImagesBean> adapter;
    private PhotoInfo.DataBean photoInfo;
    private TextView tv_content;

    @Override
    protected void findViews() {
        backTv= (ImageView) findViewById(R.id.iv_fanhui);
        gridview = (CommonDetails_XiangCe_GridView)findViewById(R.id.gridview);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_content = (TextView) findViewById(R.id.tv_content);
        iv_touxiang = (ImageView) findViewById(R.id.iv_touxiang);
    }

    @Override
    protected void initEvent() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private String timedate(int time) {
        Date data = new Date(time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String times = sdf.format(data);
        return times;
    }
    @Override
    protected void init() {
        Intent intent = getIntent();
        photoInfo = (PhotoInfo.DataBean) intent.getSerializableExtra("photoInfo");
        PhotoInfo.DataBean.UserBean user = photoInfo.getUser();
        tv_name     .setText(user.getNickname());
        tv_address  .setText(photoInfo.getTitle());
        tv_content  .setText(photoInfo.getContent());

        tv_time     .setText(timedate(Integer.parseInt(photoInfo.getCreate_time())));
        //圆形头像
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_stub);
        Bitmap circleImage = BitmapUtil.createCircleImage(bitmap);
        iv_touxiang.setImageBitmap(circleImage);

        List<PhotoInfo.DataBean.ImagesBean> imagesList = photoInfo.getImages();
        adapter = new CommonAdapter<PhotoInfo.DataBean.ImagesBean>(this, imagesList,
                R.layout.adapter_photo_details_item) {
            @Override
            public void fillData(ViewHolder helper, int position, PhotoInfo.DataBean.ImagesBean item) {
                // TODO 填充数据

                helper.setImageByUrl(R.id.imageView,item.getImage_path());

            }
        };
        gridview.setAdapter(adapter);


    }


    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_photo_details;
    }
}
