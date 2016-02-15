package io.github.cellzer.yuezhihu.yuezhihu.ui.fragment;

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

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.github.cellzer.yuezhihu.yuezhihu.Constant;
import io.github.cellzer.yuezhihu.yuezhihu.R;
import io.github.cellzer.yuezhihu.yuezhihu.net.HttpUtils;
import io.github.cellzer.yuezhihu.yuezhihu.util.PreUtils;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

/**
 * Created by walmand_ on 2016/1/31 0031.
 * 新奇日报一级页面
 */
public class TopicNewsFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    @InjectView(R.id.id_tablayout)
    TabLayout mTabLayout;
    @InjectView(R.id.id_appbarlayout)
    AppBarLayout mAppbarlayout;
    @InjectView(R.id.id_viewpager)
    ViewPager mViewPager;
    @InjectView(R.id.id_coordinatorlayout)
    CoordinatorLayout mCoordinatorlayout;


    private TopicNewsPagerAdapter mTopicNewsPagerAdapter;
    private List<String> mTitles = new ArrayList<>();
    private List<String> mIds = new ArrayList<>();
//    private List<NewsFragment> mFragmentsList = new ArrayList<NewsFragment>();


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_news, null);
        ButterKnife.inject(view);

        return view;
    }

    @Override
    protected void initData() {
        if (HttpUtils.checkNetwork(mActivity)) {
            HttpUtils.get(Constant.THEMES, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    String json = response.toString();
                    PreUtils.putStringToDefault(mActivity, Constant.THEMES, json);
                    parseJson(response);
                }
            });

        } else {
            String json = PreUtils.getStringFromDefault(mActivity, Constant.THEMES, "");
            try {
                JSONObject jsonObject = new JSONObject(json);
                parseJson(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }


    private void configView() {
        mTopicNewsPagerAdapter = new TopicNewsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mTopicNewsPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(this);

        mTabLayout.setTabMode(MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mTopicNewsPagerAdapter);


    }

    private void parseJson(JSONObject response) {
        try {
            JSONArray itemsArray = response.getJSONArray("others");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject itemObject = itemsArray.getJSONObject(i);
                mTitles.add(itemObject.getString("name"));
                mIds.add(itemObject.getString("id"));
                configView();
                mTopicNewsPagerAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

     class TopicNewsPagerAdapter extends FragmentStatePagerAdapter {

        public TopicNewsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return new NewsFragment(mIds.get(position),mTitles.get(position),mActivity);
        }

        @Override
        public int getCount() {
            return mTitles.size();
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        ((MainActivity)mActivity).setToolbarTitle(mTitles[position]);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
