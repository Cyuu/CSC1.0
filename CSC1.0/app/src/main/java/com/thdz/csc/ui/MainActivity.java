package com.thdz.csc.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.thdz.csc.R;
import com.thdz.csc.base.BaseActivity;
import com.thdz.csc.base.BaseFragment;
import com.thdz.csc.fragment.AlarmFragment;
import com.thdz.csc.fragment.MapFragment;
import com.thdz.csc.fragment.MineFragment;
import com.thdz.csc.fragment.MonitorFragment;
import com.thdz.csc.view.BottomNavigationViewHelper;

/**
 * Google的BottomNavigationView，底部menu
 */
public class MainActivity extends BaseActivity {


    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private AlarmFragment alarmFragment;
    private MonitorFragment monitorFragment;
    private MapFragment mapFragment;
    private MineFragment mineFragment;

    private BaseFragment currentFragment;

    private int currentIndex = -1;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main222);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        fragmentManager = getSupportFragmentManager();
        checkTab(0);

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 底部导航栏切换
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_alarm:
                    checkTab(0);
                    return true;
                case R.id.navigation_monitor:
                    checkTab(1);
                    return true;
                case R.id.navigation_map:
                    checkTab(2);
                    return true;
                case R.id.navigation_mine:
                    checkTab(3);
                    return true;
            }
            return false;
        }
    };

    /**
     *
     * @param index 0 告警， 1 监控点， 2 地图， 3 我的
     */
    private void checkTab(int index) {

        boolean fromRight2Left = true;
        if (index < currentIndex) {
            fromRight2Left = false;
        }
        if (currentIndex == index) {
            return;
        }

        // 重置按钮
        transaction = fragmentManager.beginTransaction();// 开启一个Fragment事务
        if (fromRight2Left) {
            transaction.setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out);
        } else {
            transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_right_out);
        }
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments();

        switch (index) {
            case 0:
                if (alarmFragment == null) {
                    alarmFragment = new AlarmFragment();
                    transaction.add(R.id.container, alarmFragment);
                } else {
                    transaction.show(alarmFragment);
                }
                transaction.commit();
                currentFragment = alarmFragment;
                currentIndex = 0;
                break;
            case 1:
                if (monitorFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    monitorFragment = new MonitorFragment();
                    transaction.add(R.id.container, monitorFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(monitorFragment);
                }
                transaction.commit();
                currentFragment = monitorFragment;
                currentIndex = 1;
                break;
            case 2:
                if (mapFragment == null) {
                    mapFragment = new MapFragment();
                    transaction.add(R.id.container, mapFragment);
                } else {
                    transaction.show(mapFragment);
                }
                transaction.commit();
                currentFragment = mapFragment;
                currentIndex = 2;
                break;
            case 3:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.container, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                transaction.commit();
                currentFragment = mineFragment;
                currentIndex = 3;
                break;
        }
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     */
    private void hideFragments() {
        if (transaction == null) {
            transaction = fragmentManager.beginTransaction();
        }
        if (alarmFragment != null) {
            transaction.hide(alarmFragment);
        }
        if (monitorFragment != null) {
            transaction.hide(monitorFragment);
        }
        if (mapFragment != null) {
            transaction.hide(mapFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

}
