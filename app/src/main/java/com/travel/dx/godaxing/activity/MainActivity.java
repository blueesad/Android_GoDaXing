package com.travel.dx.godaxing.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.modules.home.activity.HomeFragment;
import com.travel.dx.godaxing.modules.me.activity.MeFragment;
import com.travel.dx.godaxing.modules.near.activity.NearFragment;
import com.travel.dx.godaxing.modules.shop.activity.ShopFragment;
import com.travel.dx.godaxing.widget.BottomMenu;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private boolean isFirst = true;
    private long lastTime = 0;
    private BottomMenu bmHome;
    private BottomMenu bmNear;
    private BottomMenu bmShop;
    private BottomMenu bmMe;
    private HomeFragment homeFragment;
    private ShopFragment shopFragment;
    private NearFragment nearFragment;
    private MeFragment meFragment;
    private BottomMenu lastSelectMenu;
    @Override
    protected void findViews() {
        bmHome = (BottomMenu) findViewById(R.id.home_bm);
        bmNear = (BottomMenu) findViewById(R.id.near_bm);
        bmShop = (BottomMenu) findViewById(R.id.shop_bm);
        bmMe = (BottomMenu) findViewById(R.id.me_bm);
    }

    @Override
    protected void initEvent() {
        bmHome.setOnClickListener(this);
        bmNear.setOnClickListener(this);
        bmShop.setOnClickListener(this);
        bmMe.setOnClickListener(this);
    }

    @Override
    protected void init() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        homeFragment = new HomeFragment();
        nearFragment = new NearFragment();
        shopFragment = new ShopFragment();
        meFragment = new MeFragment();
        // 将对应的菜单和fragment进行绑定，以便于在点击菜单的时候，将其对应的fragment设置为show状态
        bmHome.setFragment(homeFragment);
        bmNear.setFragment(nearFragment);
        bmShop.setFragment(shopFragment);
        bmMe.setFragment(meFragment);
        transaction.add(R.id.fragment_container, meFragment);
        transaction.add(R.id.fragment_container, shopFragment);
        transaction.add(R.id.fragment_container, nearFragment);
        transaction.add(R.id.fragment_container, homeFragment);
        transaction.hide(nearFragment);
        transaction.hide(meFragment);
        transaction.hide(shopFragment);
        transaction.commit();
        // 默认把首页设置为选中状态
        bmHome.selectMenu();
        lastSelectMenu = bmHome;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    public void onBackPressed() {

        if (isFirst) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            lastTime = System.currentTimeMillis();
            isFirst = false;
        } else {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime <= 2000) {
                finish();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                lastTime = System.currentTimeMillis();

            }
        }
    }

    @Override
    public void onClick(View v) {
        if (lastSelectMenu == v){
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (lastSelectMenu != null){
            lastSelectMenu.unSelectMenu();
            transaction.hide(lastSelectMenu.getFragment());
        }
        ((BottomMenu) v).selectMenu();
        transaction.show(((BottomMenu) v).getFragment());
        lastSelectMenu = ((BottomMenu) v);

        transaction.commit();
    }
}
