package com.travel.dx.godaxing.modules.home.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.BaseFragment;
import com.travel.dx.godaxing.activity.CommonDetailsActivity;
import com.travel.dx.godaxing.activity.SearchActivity;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.i.DetailsType;
import com.travel.dx.godaxing.modules.home.adapter.MyRollAdapter;
import com.travel.dx.godaxing.modules.home.bean.HomeDetailsInfo;
import com.travel.dx.godaxing.modules.home.bean.HomeRollData;
import com.travel.dx.godaxing.modules.home.bean.PlayTogeInfo;
import com.travel.dx.godaxing.modules.home.dao.HomeDetailsRullDao;
import com.travel.dx.godaxing.widget.MyScrollView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class HomeFragment extends BaseFragment {
    private RollPagerView rollPagerView;
    private RadioGroup radioGroup;
    private Fragment commend;
    private Fragment together;
    private TextView Trendstv;
    private TextView Routestv;
    private TextView Hoteltv;
    private TextView ScenceTv;
    private TextView shaituTv;
    private TextView yueJiCard;
    private TextView playtogeTV;
    private TextView tv_sousuo;
    private RelativeLayout linearLayout;
    private RelativeLayout home_dianhua;
    private List<HomeRollData.DataEntity.AdvEntity> dataBean;
    private View kefuDialogView;
    private AlertDialog kefuDialog;
    private TextView tv_kepu;
    private Button btn_queding;
    private Button btn_quxiao;
    private MyScrollView home_scrollView;
    private int type = 0;
    private ImageView iv_totop;

    @Override
    protected void findViews(View view) {
        rollPagerView = (RollPagerView) view.findViewById(R.id.banner_view_pager);
        radioGroup = (RadioGroup) view.findViewById(R.id.near_radiogroup);
        Trendstv = (TextView) view.findViewById(R.id.travel_trends);
        Routestv = (TextView) view.findViewById(R.id.luxian_tv);
        Hoteltv = (TextView) view.findViewById(R.id.hotel_tv);
        ScenceTv = (TextView) view.findViewById(R.id.scene);
        shaituTv = (TextView) view.findViewById(R.id.shaitu);
        yueJiCard = (TextView) view.findViewById(R.id.yue_ji);
        home_dianhua = (RelativeLayout) view.findViewById(R.id.home_dianhua);
        playtogeTV = (TextView) view.findViewById(R.id.playtogether_tv);
        tv_sousuo = (TextView) view.findViewById(R.id.tv_sousuo);
        linearLayout = (RelativeLayout) view.findViewById(R.id.search_ll);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        kefuDialogView = inflater.inflate(R.layout.layout_kefu_dialog, null);
        btn_queding = (Button) kefuDialogView.findViewById(R.id.btn_queding);
        btn_quxiao = (Button) kefuDialogView.findViewById(R.id.btn_quxiao);
        home_scrollView = (MyScrollView) view.findViewById(R.id.home_scrollView);
        tv_kepu = (TextView) view.findViewById(R.id.tv_kepu);
        iv_totop = (ImageView) view.findViewById(R.id.iv_totop);
    }

    @Override
    protected void initEvent() {
        iv_totop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_scrollView.scrollTo(0,0);
            }
        });
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri teluri = Uri.parse("tel:4008191937");
                intent.setData(teluri);
                startActivity(intent);
                kefuDialog.dismiss();
            }
        });
        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kefuDialog.dismiss();

            }
        });
        tv_kepu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), KePuActivity.class));
            }
        });
        Trendstv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  跳转到旅游动态页面
                Intent intent = new Intent(getActivity(), TrendsActivity.class);
                startActivity(intent);
            }
        });
        Routestv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  跳转到旅游线路页面
                Intent intent = new Intent(getActivity(), RoutesActivity.class);
                startActivity(intent);
            }
        });
        Hoteltv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  跳转到酒店页面
                Intent intent = new Intent(getActivity(), HotelActivity.class);
                startActivity(intent);
            }
        });
        ScenceTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  跳转到景点页面
                Intent intent = new Intent(getActivity(), ScenceActivity.class);
                startActivity(intent);
            }
        });
        shaituTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  跳转到晒图页面
                Intent intent = new Intent(getActivity(), SharePhotoActivity.class);
                startActivity(intent);
            }
        });
        playtogeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayTogetherActivity.class);
                startActivity(intent);

            }
        });
        yueJiCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YueJiCardActivity.class);
                startActivity(intent);
            }
        });
        home_dianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kefuDialog.show();
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.home_tuijian) {
                    type = 0;
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.home_container_layout, commend);

                    transaction.commit();
                }
                if (checkedId == R.id.home_together) {
                    type = 1;
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.home_container_layout, together);

                    transaction.commit();
                }
            }
        });

        tv_sousuo.setOnClickListener(new View.OnClickListener() {
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
                        Intent intent = new Intent(getActivity(), SearchActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

            }
        });
        rollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), CommonDetailsActivity.class);
                    int tid = Integer.parseInt(dataBean.get(position).getTid());
                    intent.putExtra("id", tid);
                    intent.putExtra("type", DetailsType.TYPE_PLAY);
                    startActivity(intent);

                }
                if (position == 1) {
                    // Toast.makeText(getActivity(), "点击了1！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), TrendsDetailsActivity.class);
                    String tid = dataBean.get(position).getTid();
                    intent.putExtra("id", tid);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(getActivity(), RoutesDetailsActivity.class);
                    String tid = dataBean.get(position).getTid();
                    intent.putExtra("id", tid);
                    startActivity(intent);
                }
            }
        });
        home_scrollView.setListener(new MyScrollView.ScrollChangeUpdateListener() {

            @Override
            public void onScrollBottom() {
                //TODO 下拉加载
                if (type == 0) {
                    //TODO 发送到Fragment
                    EventBus.getDefault().post(new HomeDetailsInfo());
                } else if (type == 1) {
                    EventBus.getDefault().post(new PlayTogeInfo());
                }
            }

            @Override
            public void ScrollScreenHeight() {
                iv_totop.setVisibility(View.VISIBLE);
            }

            @Override
            public void unScrollScreenHeight() {
                iv_totop.setVisibility(View.GONE);
            }


        });
    }

    @Override
    protected void init() {
        rollPagerView.setHintView(new ColorPointHintView(getActivity(), Color.GREEN, Color.WHITE));
        rollPagerView.setPlayDelay(4000);

        commend = new CommendHomeFragment();
        together = new TogetherHomeFragment();
        kefuDialog = new AlertDialog.Builder(getActivity())
                .setView(kefuDialogView)
                .setCancelable(false)
                .create();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.home_container_layout, commend);
        transaction.add(R.id.home_container_layout, together);
        transaction.hide(together);
        transaction.commit();

    }

    @Override
    protected void loadData() {
        HomeDetailsRullDao.requestCommendItemList(new BaseCallBack() {
            @Override
            public void success(Object data) {
                dataBean = (List<HomeRollData.DataEntity.AdvEntity>) data;

                MyRollAdapter adapter = new MyRollAdapter(rollPagerView, getActivity(), dataBean);
                rollPagerView.setAdapter(adapter);
            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(getActivity(), "数据加载失败，请重新载入", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home_layout;
    }

}

