package io.github.cellzer.yuezhihu.yuezhihu.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.github.cellzer.yuezhihu.yuezhihu.Constant;
import io.github.cellzer.yuezhihu.yuezhihu.R;

/**
 * Created by walmand_ on 2016/2/18 0018.
 */
public class TopUserMainFragment extends BaseFragment {
    @InjectView(R.id.tablayout)
    TabLayout mTabLayout;
    @InjectView(R.id.viewpager)
    ViewPager mViewPager;
    private TopUserMainPagerAdapter mTopUserMainPagerAdapter;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_topuser_main, null);
        return view;
    }

    @Override
    protected void initData() {
        configView();
    }



    private void configView() {
        mTopUserMainPagerAdapter = new TopUserMainPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mTopUserMainPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mTopUserMainPagerAdapter);


    }


    class TopUserMainPagerAdapter extends FragmentStatePagerAdapter {

        public TopUserMainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return  Constant.USER_TITLE[position];
        }

        @Override
        public Fragment getItem(int position) {
            return new TopUserFragment(Constant.USER_URL[position],mActivity);
        }

        @Override
        public int getCount() {
            return Constant.USER_TITLE.length;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    /**
     * 这段可以解决fragment嵌套fragment会崩溃的问题
     */
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            //参数是固定写法
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
