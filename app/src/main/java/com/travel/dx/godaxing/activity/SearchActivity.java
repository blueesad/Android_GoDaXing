package com.travel.dx.godaxing.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.adapter.SearchAdapter;
import com.travel.dx.godaxing.bean.SearchInfo;
import com.travel.dx.godaxing.dao.HotSearchDao;
import com.travel.dx.godaxing.i.BaseCallBack;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity{


    private ImageView back;
    private Button search;
    private EditText editText;
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private List<SearchInfo> list;
    private LinearLayout linearLayout;
    private String key;
    private ProgressDialog dialog;
    private InputMethodManager manager;

    @Override
    protected void findViews() {
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        editText = (EditText) findViewById(R.id.search_et);
        back = (ImageView) findViewById(R.id.back_search);
        search = (Button) findViewById(R.id.search_btn);
        recyclerView = (RecyclerView) findViewById(R.id.search_recycleview);
        linearLayout = (LinearLayout) findViewById(R.id.search_hot);
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
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
                key = editText.getText().toString();
                Intent intent = new Intent(SearchActivity.this,HotSearchActivity.class);
                intent.putExtra("key",key);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void init() {
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this,3));
        dialog = new ProgressDialog(this);
        dialog.setMessage("疯狂加载中");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

    }

    @Override
    protected void loadData() {

        HotSearchDao.requestHotSearchList(new BaseCallBack() {
            @Override
            public void success(Object data) {
                dialog.dismiss();
                list = (List<SearchInfo>) data;
                adapter = new SearchAdapter(SearchActivity.this,list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void failed(int errorCode, Object data) {
                dialog.dismiss();
                Toast.makeText(SearchActivity.this, "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search;
    }

}
