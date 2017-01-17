package com.travel.dx.godaxing.modules.me.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.dx.godaxing.DaXingApplication;
import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseActivity;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.me.bean.MeansInfo;
import com.travel.dx.godaxing.modules.me.dao.MeansDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MymeansActivity extends BaseActivity implements View.OnClickListener {


    private ImageView iVtouxiang;

    private TextView tVnicheng;

    private TextView tVxingbie;

    private TextView tVqianming;

    private TextView tVshouji;

    private TextView tVyouxiang;

    private String Mobile;

    private SharedPreferences sp;

    private Editor edit;

    private RelativeLayout touxianglayout;

    private PopupWindow popupWindow;

    private View contentView;

    private final int REQUEST_CODE_IMAGE = 0;

    public final static int PHOTO_ZOOM = 2;// 从相册中选择

    public final static int PHOTO_RESULT = 3;// 结果

    public static final String IMAGE_UNSPECIFIED = "image/*";

    private String touxiangpath;
    private static  String path;






    @Override
    protected void findViews() {
        iVtouxiang = (ImageView) findViewById(R.id.iv_touxiang);

        tVnicheng = (TextView) findViewById(R.id.tv_nicheng);

        tVxingbie = (TextView) findViewById(R.id.tv_xingbie);

        tVqianming = (TextView) findViewById(R.id.tv_qianming);

        tVshouji = (TextView) findViewById(R.id.tv_shouji);

        tVyouxiang = (TextView) findViewById(R.id.tv_youxian);

        touxianglayout = (RelativeLayout) findViewById(R.id.touxiang);


    }

    @Override
    protected void initEvent() {

        //数据库初始化
        sp = getSharedPreferences("my_save", MymeansActivity.MODE_APPEND);
        edit = sp.edit();


        touxianglayout.setOnClickListener(this);
        Log.i("ischoosexiangce", " "+DaXingApplication.isLogin);
        if (DaXingApplication.isLogin) {
            Log.i("path", " "+path);
            iVtouxiang.setImageBitmap(BitmapFactory.decodeFile(path));
        } else  {

            iVtouxiang.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/touxiang.jpg/"));

        }


    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadData() {
        String token = DaXingApplication.token;

        MeansDao.requestCommendMeans(token, new BaseCallBack() {


            @Override
            public void success(Object data) {
                MeansInfo meansinfo = (MeansInfo) data;
                Log.i("MeansloadData", "data" + data.toString());

                if (TextUtils.isEmpty(sp.getString("Mobile", null))
                        && TextUtils.isEmpty(sp.getString("Nickname", null))
                        && TextUtils.isEmpty(sp.getString("Ind_signature", null))
                        && TextUtils.isEmpty(sp.getString("Sex", null))
                        && TextUtils.isEmpty(sp.getString("Email", null))) {
                    Mobile = meansinfo.getMobile();
                    Log.i("putMobile", " " + Mobile);
                    edit.putString("Mobile", Mobile);
                    edit.putString("Nickname", meansinfo.getNickname());
                    edit.putString("Ind_signature", meansinfo.getInd_signature());
                    edit.putString("Sex", meansinfo.getSex());
                    edit.putString("Email", meansinfo.getEmail());
                    edit.commit();


                }


                Log.i("getMobile", " " + sp.getString("Mobile", null));

                tVshouji.setText(sp.getString("Mobile", null));
                tVnicheng.setText(sp.getString("Nickname", null));
                tVqianming.setText(sp.getString("Ind_signature", null));
                tVxingbie.setText(sp.getString("Sex", null));
                tVyouxiang.setText(sp.getString("Email", null));


                Log.i("MeansloadData", "Mobile" + meansinfo.getMobile());


            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(MymeansActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_mymeans;
    }


    @Override
    public void onClick(View v) {

        contentView = getLayoutInflater().inflate(R.layout.pop_wind_layout, null);
        Button cancelbtn = (Button) contentView.findViewById(R.id.cancel_btn);
        cancelbtn.setOnClickListener(new ImageView.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        //拍照
        Button paizhaobtn = (Button) contentView.findViewById(R.id.paizhao_btn);
        paizhaobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/touxiang.jpg/")));
                Log.i("path", Environment.getExternalStorageDirectory() + "/touxiang.jpg/");
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }

            }
        });
        //从相册选择
        Button xiangcebtn = (Button) contentView.findViewById(R.id.xuanzhe_btn);
        xiangcebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PHOTO_ZOOM);


            }
        });


        PopupWindow(v);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //判断是否是返回键按下，如果PopupWindow正在显示，让popwindow消失
            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
            } else {
                //返回键按下的时候，没有PopupWindow，直接退出activity
                finish();
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_IMAGE) {
                //TODO 处理拍照结果
                if (data == null) {
                    //用户指定了照片的保存路径，可以获取到图片的原图
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/touxiang.jpg/", options);
                    iVtouxiang.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/touxiang.jpg/"));
                    Log.i("resultpath", Environment.getExternalStorageDirectory() + "/touxiang.jpg/");
                } else {
                    Bundle extras = data.getExtras();
                    Bitmap bm = extras.getParcelable("data");//获取拍照返回的数据
                    if (bm != null) {
                        Log.i("REQUEST_CODE_IMAGE", "ischoosexiangce: " + DaXingApplication.isLogin);
                        DaXingApplication.isLogin=false;
                        iVtouxiang.setImageBitmap(bm);
                        saveMyBitmap(bm, "touxiang");

                        if (popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                    }


                }
            } else if (requestCode == PHOTO_ZOOM) {
                // 从相册返回的数据
                if (data != null) {

                    Log.i("data", " " + data.getData());

                    Uri uri = data.getData();// 使用getData方法获取要调用的接口
                    // 第二个参数表示要查询的数据的字段名
                    Cursor c = getContentResolver().query(uri,
                            new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                    if (c != null) {
                        c.moveToFirst();
                        // 通过游标来获取名为MediaStore.Images.Media.DATA字段的值
                        path = c.getString(c
                                .getColumnIndex(MediaStore.Images.Media.DATA));
                        Log.i("onActivityResult", "path: " + path);

                        iVtouxiang.setImageBitmap(BitmapFactory.decodeFile(path));
                        Log.i("PHOTO_ZOOM", "ischoosexiangce: " + DaXingApplication.isLogin);
                        DaXingApplication.isLogin=true;
//                        Bitmap xiangcebitmap = BitmapFactory.decodeFile(path);
//                        saveMyBitmap(xiangcebitmap,"touxiang" );
                        if (popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                    }

                }


            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }


    public void saveMyBitmap(Bitmap mBitmap, String bitName) {
        touxiangpath = Environment.getExternalStorageDirectory() + bitName + ".jpg";
        File f = new File(touxiangpath);

        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void photoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true"); //设置了参数，就会调用裁剪，如果不设置，就会跳过裁剪的过程。
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);//这个是裁剪时候的 裁剪框的 X 方向的比例。
        intent.putExtra("aspectY", 1); //同上Y 方向的比例. (注意： aspectX, aspectY ，两个值都需要为 整数，如果有一个为浮点数，就会导致比例失效。)
//设置aspectX 与 aspectY 后，裁剪框会按照所指定的比例出现，放大缩小都不会更改。如果不指定，那么 裁剪框就可以随意调整了。
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 250); //返回数据的时候的 X 像素大小。
        intent.putExtra("outputY", 250); //返回的时候 Y 的像素大小。
        //intent.putExtra("noFaceDetection", true); 是否去除面部检测， 如果你需要特定的比例去裁剪图片，那么这个一定要去掉，因为它会破坏掉特定的比例。
        intent.putExtra("return-data", true);//是否要返回值。 一般都要。
        startActivityForResult(intent, PHOTO_RESULT);
    }


    //判断sd卡是否挂载
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public void PopupWindow(View v) {

        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        //设置点击其他区域让popwindow消失 ,这个设置必须和setBackgroundDrawable一起设置才有用，
        popupWindow.setOutsideTouchable(true);
        //如果传入null,就不能达到效果
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));


        //将window显示到指定位置：屏幕的底部居中的位置
        popupWindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 160, 0);

    }


}
