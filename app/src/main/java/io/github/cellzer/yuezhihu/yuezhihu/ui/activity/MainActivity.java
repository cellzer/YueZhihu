package io.github.cellzer.yuezhihu.yuezhihu.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;


import butterknife.ButterKnife;
import butterknife.InjectView;
import io.github.cellzer.yuezhihu.yuezhihu.R;
import io.github.cellzer.yuezhihu.yuezhihu.YueZhihuApplication;
import io.github.cellzer.yuezhihu.yuezhihu.db.CacheDbHelper;
import io.github.cellzer.yuezhihu.yuezhihu.ui.fragment.ChosenMainFragment;
import io.github.cellzer.yuezhihu.yuezhihu.ui.fragment.MainFragment;
import io.github.cellzer.yuezhihu.yuezhihu.ui.fragment.TopUserMainFragment;
import io.github.cellzer.yuezhihu.yuezhihu.ui.fragment.TopicNewsFragment;


public class MainActivity extends BaseActivity  {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.fl_content)
    FrameLayout flContent;
    @InjectView(R.id.id_navigationview)
    NavigationView mNavigationview;
    @InjectView(R.id.id_drawerlayout)
    DrawerLayout mDrawerlayout;
    private SharedPreferences sp;

//    @InjectView(R.id.title)
//    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        dbHelper = new CacheDbHelper(this, 1);


        sp = PreferenceManager.getDefaultSharedPreferences(YueZhihuApplication.getContext());
        isLight = sp.getBoolean("isLight", true);


        config();
        replaceView(new MainFragment(),"latest");
    }

    private void config() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerlayout,toolbar,R.string.hello_world,R.string.hello_world);
        toggle.syncState();
        mDrawerlayout.setDrawerListener(toggle);

        //给NavigationView填充顶部区域，也可在xml中使用app:headerLayout="@layout/header_nav"来设置
        mNavigationview.inflateHeaderView(R.layout.header_nav);
        //给NavigationView填充Menu菜单，也可在xml中使用app:menu="@menu/menu_nav"来设置
        mNavigationview.inflateMenu(R.menu.menu_nav);
        //设置监听
        onNavgationViewMenuItemSelected(mNavigationview);



    }
    /**
     * 设置NavigationView中menu的item被选中后要执行的操作
     *
     * @param mNav
     */
    private void onNavgationViewMenuItemSelected(NavigationView mNav) {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                String msgString = "";

                switch (menuItem.getItemId()) {
                    case R.id.mainPage:
                        replaceView(new MainFragment(),"latest");
                        msgString = (String) menuItem.getTitle();
                        menuItem.setChecked(true);
                        break;

                    case R.id.newsPage:
                        replaceView(new TopicNewsFragment(),"news");
                        msgString = (String) menuItem.getTitle();
                        menuItem.setChecked(true);
                        break;
                    case R.id.chosen:
                        replaceView(new ChosenMainFragment(),"chosen");
                        msgString = (String) menuItem.getTitle();
                        menuItem.setChecked(true);
                        break;
                    case R.id.ranking:
                        replaceView(new TopUserMainFragment(),"topuser");
                        msgString = (String) menuItem.getTitle();
                        menuItem.setChecked(true);
                        break;
                    case R.id.setting:
                        replaceView(new SettingFragment(),"setting");
                        msgString = (String) menuItem.getTitle();
                        menuItem.setChecked(true);
                        break;
                    case R.id.about:
                        replaceView(new AboutFragment(),"about");
                        msgString = (String) menuItem.getTitle();
                        menuItem.setChecked(true);
                        break;

                }

                toolbar.setTitle(msgString);
                mDrawerlayout.closeDrawers();
                return true;
            }
        });
    }
    private boolean isLight;
    private long firstTime;
    public boolean isLight() {
        return isLight;
    }

    public void setToolbarTitle(String text) {
        toolbar.setTitle(text);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerlayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerlayout.closeDrawers();
        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Snackbar sb = Snackbar.make(flContent, "再按一次退出", Snackbar.LENGTH_SHORT);
                sb.getView().setBackgroundColor(getResources().getColor(isLight ? android.R.color.holo_blue_dark : android.R.color.black));
                sb.show();
                firstTime = secondTime;
            } else {
                finish();
            }
        }

    }
    private CacheDbHelper dbHelper;
    public CacheDbHelper getCacheDbHelper() {
        return dbHelper;
    }

    private void replaceView(Fragment f,String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,f,tag).commit();

    }
}
