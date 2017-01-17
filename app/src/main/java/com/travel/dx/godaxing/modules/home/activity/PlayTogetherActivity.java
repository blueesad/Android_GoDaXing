package com.travel.dx.godaxing.modules.home.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseActivity;
import com.travel.dx.godaxing.activity.SearchActivity;
import com.travel.dx.godaxing.adapter.CommonAdapter;
import com.travel.dx.godaxing.modules.home.bean.PlayTogeInfo;

import java.util.List;

public class PlayTogetherActivity extends BaseActivity {
    private SwipeRefreshLayout refreshLayout;
    private ListView listView;
    private List<PlayTogeInfo> list;
    private CommonAdapter adapter;

    private TextView search;
    private LinearLayout linearLayout;
    private RadioGroup playtogertherRG;
    private Fragment inprogress;
    private Fragment pastcampaign;


//     java.lang.ClassCastException: java.util.ArrayList cannot be cast to com.travel.dx.godaxing.modules.home.bean.YueJiCardInfo$DataBean



    @Override
    protected void findViews() {
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        linearLayout = (LinearLayout)findViewById(R.id.search_ll);
        listView = (ListView)findViewById(R.id.shop_lv);
        search = (TextView) findViewById(R.id.search);
        playtogertherRG = (RadioGroup) findViewById(R.id.play_together_radiogroup);

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
                    public void onAnimationStart(Animation animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(PlayTogetherActivity.this,SearchActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });


    playtogertherRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId==R.id.play_wangqi){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.hide(inprogress);
                transaction.show(pastcampaign);
                transaction.commit();
            }

            if (checkedId == R.id.play_doing) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.hide(pastcampaign);
                transaction.show(inprogress);
                transaction.commit();
            }
        }
    });


    }

    @Override
    protected void init() {


        inprogress = new InProgressfragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container_layout, inprogress);
        pastcampaign = new PastCampaignframent();
        transaction.add(R.id.container_layout, pastcampaign);
        transaction.hide(pastcampaign);
        transaction.commit();

    }

    @Override
    protected void loadData() {



    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_play_together;
    }
}
