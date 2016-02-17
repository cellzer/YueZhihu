package io.github.cellzer.yuezhihu.yuezhihu.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.InjectView;
import io.github.cellzer.yuezhihu.yuezhihu.Constant;
import io.github.cellzer.yuezhihu.yuezhihu.R;


/**
 * Created by walmand_ on 2016/2/16 0016.
 * 知乎精选一级页面
 */
public class ChosenMainFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    @InjectView(R.id.tablayout)
    TabLayout mTabLayout;
    @InjectView(R.id.viewpager)
    ViewPager mViewPager;
    private ChosenMainPagerAdapter mChosenMainPagerAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_chosen_main, null);
        ButterKnife.inject(view);
        return view;
    }


    @Override
    protected void initData() {
        configView();
    }



    private void configView() {
        mChosenMainPagerAdapter = new ChosenMainPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mChosenMainPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(this);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mChosenMainPagerAdapter);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class ChosenMainPagerAdapter extends FragmentStatePagerAdapter {

        public ChosenMainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return  Constant.CHOSEN_TITLE[position];
        }

        @Override
        public Fragment getItem(int position) {
            return new ChosenFragment(Constant.CHOSEN_URL[position],mActivity);
        }

        @Override
        public int getCount() {
            return Constant.CHOSEN_TITLE.length;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
