package io.github.cellzer.yuezhihu.yuezhihu.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;

import io.github.cellzer.yuezhihu.yuezhihu.Constant;
import io.github.cellzer.yuezhihu.yuezhihu.R;
import io.github.cellzer.yuezhihu.yuezhihu.YueZhihuApplication;
import io.github.cellzer.yuezhihu.yuezhihu.adapter.NewsItemAdapter;
import io.github.cellzer.yuezhihu.yuezhihu.db.CacheDbHelper;
import io.github.cellzer.yuezhihu.yuezhihu.model.News;
import io.github.cellzer.yuezhihu.yuezhihu.model.StoriesEntity;
import io.github.cellzer.yuezhihu.yuezhihu.net.HttpUtils;
import io.github.cellzer.yuezhihu.yuezhihu.ui.activity.MainActivity;
import io.github.cellzer.yuezhihu.yuezhihu.ui.activity.NewsContentActivity;
import io.github.cellzer.yuezhihu.yuezhihu.util.PreUtils;


/**
 * 新奇日报二级页面
 */
@SuppressLint("ValidFragment")
public class NewsFragment extends BaseFragment {
    private ImageLoader mImageLoader;
    private ListView lv_news;
    private ImageView iv_title;
    private TextView tv_title;
    private String urlId;
    private News news;
    private NewsItemAdapter mAdapter;
    private String title;
    private CacheDbHelper dbHelper ;
    private Context context;

    public NewsFragment(String id, String title ,Context context) {
        urlId = id;
        this.title = title;
        this.context = context;
        dbHelper = new CacheDbHelper(context, 1);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_layout, container, false);
        //禁用刷新
        ((MainActivity) mActivity).setSwipeRefreshEnable(false);

        mImageLoader = ImageLoader.getInstance();
        lv_news = (ListView) view.findViewById(R.id.lv_news);
        View header = LayoutInflater.from(mActivity).inflate(
                R.layout.news_header, lv_news, false);
        iv_title = (ImageView) header.findViewById(R.id.iv_title);
        tv_title = (TextView) header.findViewById(R.id.tv_title);
        lv_news.addHeaderView(header);
        lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int[] startingLocation = new int[2];
                view.getLocationOnScreen(startingLocation);
                startingLocation[0] += view.getWidth() / 2;
                StoriesEntity entity = (StoriesEntity) parent.getAdapter().getItem(position);
                Intent intent = new Intent(mActivity, NewsContentActivity.class);
                intent.putExtra(Constant.START_LOCATION, startingLocation);
                intent.putExtra("entity", entity);

                intent.putExtra("isLight", PreferenceManager.getDefaultSharedPreferences(YueZhihuApplication.getContext()).getBoolean("isLight", true));

                String readSequence = PreUtils.getStringFromDefault(mActivity, "read", "");
                String[] splits = readSequence.split(",");
                StringBuffer sb = new StringBuffer();
                if (splits.length >= 200) {
                    for (int i = 100; i < splits.length; i++) {
                        sb.append(splits[i] + ",");
                    }
                    readSequence = sb.toString();
                }

                if (!readSequence.contains(entity.getId() + "")) {
                    readSequence = readSequence + entity.getId() + ",";
                }
                PreUtils.putStringToDefault(mActivity, "read", readSequence);
                TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
                tv_title.setTextColor(getResources().getColor(R.color.clicked_tv_textcolor));

                startActivity(intent);
                mActivity.overridePendingTransition(0, 0);
            }
        });
        lv_news.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (lv_news != null && lv_news.getChildCount() > 0) {
//                    boolean enable = (firstVisibleItem == 0) && (view.getChildAt(firstVisibleItem).getTop() == 0);
//                    ((MainActivity) mActivity).setSwipeRefreshEnable(enable);
                }
            }
        });
        return view;
    }


    @Override
    protected void initData() {
        super.initData();
        if (HttpUtils.checkNetwork(mActivity)) {
            HttpUtils.get(Constant.THEMENEWS + urlId, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("replace into CacheList(date,json) values(" + (Constant.BASE_COLUMN + Integer.parseInt(urlId)) + ",' " + responseString + "')");
                    db.close();
                    parseJson(responseString);
                }
            });
        } else {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from CacheList where date = " + (Constant.BASE_COLUMN + Integer.parseInt(urlId)), null);
            if (cursor.moveToFirst()) {
                String json = cursor.getString(cursor.getColumnIndex("json"));
                parseJson(json);
            }
            cursor.close();
            db.close();
        }

    }

    private void parseJson(String responseString) {
        Gson gson = new Gson();
        news = gson.fromJson(responseString, News.class);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        tv_title.setText(news.getDescription());
        mImageLoader.displayImage(news.getImage(), iv_title, options);
        mAdapter = new NewsItemAdapter(mActivity, news.getStories());
        lv_news.setAdapter(mAdapter);
    }

    public void updateTheme() {
        mAdapter.updateTheme();
    }
}
