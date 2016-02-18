package io.github.cellzer.yuezhihu.yuezhihu.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.github.cellzer.yuezhihu.yuezhihu.Constant;
import io.github.cellzer.yuezhihu.yuezhihu.R;
import io.github.cellzer.yuezhihu.yuezhihu.adapter.TopUserItemAdapter;
import io.github.cellzer.yuezhihu.yuezhihu.model.TopUser;
import io.github.cellzer.yuezhihu.yuezhihu.net.HttpUtils;
import io.github.cellzer.yuezhihu.yuezhihu.util.PreUtils;
import io.github.cellzer.yuezhihu.yuezhihu.util.SnackbarUtils;

/**
 * Created by walmand_ on 2016/2/18 0018.
 */
@SuppressLint("ValidFragment")
public class TopUserFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    @InjectView(R.id.lv_topuser)
    ListView lvTopuser;
    @InjectView(R.id.sr)
    SwipeRefreshLayout sr;
    private String url;
    private Context context;
    private int currentPage = 1;
    private boolean isLoading = false;
    private int rankType ;
    private TopUserItemAdapter mAdapter;
    public TopUserFragment(String url, Context context) {
        this.url = url;
        this.context = context;
        rankType = url.equals("agree")?Constant.TYPE_AGREE:Constant.TYPE_FOLLOWER;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_topuser, null);

        return view;
    }

    @Override
    protected void initData() {
        sr.setOnRefreshListener(this);
        lvTopuser.setOnItemClickListener(this);
        lvTopuser.setOnScrollListener(this);
        loadpage(1);
    }

    private void loadpage(int page) {
        isLoading = true;
        if (HttpUtils.checkNetwork(context)){
            HttpUtils.get(Constant.TOPUSER+url+"/"+page,new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    PreUtils.putStringToDefault(mActivity, Constant.TOPUSER+url, response.toString());
                    parseJson(response.toString());
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    isLoading =false;
                    sr.setRefreshing(false);
                }
            });

        }else{
            String json = PreUtils.getStringFromDefault(mActivity, Constant.TOPUSER+url, "");
            parseJson(json);
        }

    }
    private TopUser topuser;
    private void parseJson(String response) {

        Gson gson = new Gson();
        topuser = gson.fromJson(response, TopUser.class);
        if (topuser==null||topuser.getError().contains("no result")){
            SnackbarUtils.show(mActivity, "数据获取失败，请重试");
        }else{
            topuser.setRankType(rankType);
            mAdapter = new TopUserItemAdapter(mActivity,topuser);
            lvTopuser.setAdapter(mAdapter);
        }

        isLoading =false;
        sr.setRefreshing(false);
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
    public void onRefresh() {
        if(!isLoading){
            currentPage = 1;
            loadpage(1);
        }
        sr.setRefreshing(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
                if (lvTopuser != null && lvTopuser.getChildCount() > 0) {
                    if (firstVisibleItem + visibleItemCount == totalItemCount && !isLoading) {
                        loadpage(currentPage++);
                    }
                }
    }
}
