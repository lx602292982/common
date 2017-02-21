package com.spreadwin.common.ui.activity.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.spreadwin.common.R;
import com.spreadwin.common.common.weiget.MyPagerAdapter;
import com.spreadwin.common.ui.activity.BaseActivity;
import com.spreadwin.common.ui.fragment.home.GifFragment;
import com.spreadwin.common.ui.fragment.home.JoKeFragment;
import com.spreadwin.common.ui.fragment.home.PictureFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;

public class MainActivity extends BaseActivity {
    CoordinatorTabLayout mCoordinatorTabLayout;
    @InjectView(R.id.viewpage)
    ViewPager mViewPager;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"段子", "图片", "GIF"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initFragments();
        initViewPager();
        mImageArray = new int[]{
                R.mipmap.bg_android,
                R.mipmap.bg_ios,
                R.mipmap.bg_js};
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
        };
        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        mCoordinatorTabLayout.setTitle("爆笑段子")
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mViewPager);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(JoKeFragment.newInstance(0));
        mFragments.add(PictureFragment.newInstance(1));
        mFragments.add(GifFragment.newInstance(2));
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewpage);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(mContext);
    }
}
