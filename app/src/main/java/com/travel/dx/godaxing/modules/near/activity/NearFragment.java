package com.travel.dx.godaxing.modules.near.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseFragment;
import com.travel.dx.godaxing.activity.SearchActivity;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class NearFragment extends BaseFragment {


    private RadioGroup radioGroup;
    private Fragment commend;
    private Fragment together;
    private TextView search;
    private LinearLayout linearLayout;

    @Override
    protected void findViews(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.search_ll);
        radioGroup = (RadioGroup) view.findViewById(R.id.near_radiogroup);
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
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.near_tuijian) {
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.hide(together);
                    transaction.show(commend);
                    transaction.commit();
                }
                if (checkedId == R.id.near_together) {
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.hide(commend);
                    transaction.show(together);
                    transaction.commit();
                }
            }
        });
    }

    @Override
    protected void init() {
        commend = new CommendFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.container_layout, commend);
        together = new TogetherFragment();
        transaction.add(R.id.container_layout, together);
        transaction.hide(together);
        transaction.commit();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_near_layout;
    }
}
