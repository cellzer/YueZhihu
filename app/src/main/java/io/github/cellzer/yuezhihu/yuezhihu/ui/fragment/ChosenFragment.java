package io.github.cellzer.yuezhihu.yuezhihu.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.github.cellzer.yuezhihu.yuezhihu.Constant;
import io.github.cellzer.yuezhihu.yuezhihu.R;
import io.github.cellzer.yuezhihu.yuezhihu.adapter.ChosenItemAdapter;
import io.github.cellzer.yuezhihu.yuezhihu.model.Chosen;
import io.github.cellzer.yuezhihu.yuezhihu.net.HttpUtils;
import io.github.cellzer.yuezhihu.yuezhihu.util.DateUtil;
import io.github.cellzer.yuezhihu.yuezhihu.util.PreUtils;
import io.github.cellzer.yuezhihu.yuezhihu.util.SnackbarUtils;

/**
 * Created by walmand_ on 2016/2/16 0016.
 * 知乎精选二级页面
 */
@SuppressLint("ValidFragment")
public class ChosenFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.lv_chosen)
    ListView lv_chosen;
    @InjectView(R.id.sr)
    SwipeRefreshLayout sr;
    private TextView date;
    private ChosenItemAdapter mAdapter;
    private String title;
    private Context context;
    private boolean isLoading=false;
    private View header;
    public ChosenFragment(String title, Context context) {
        this.title = title;
        this.context = context;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_chosen, null);


        header = LayoutInflater.from(context).inflate(R.layout.chosen_list_header,null);
        date = (TextView) header.findViewById(R.id.date);

        return view;
    }
    private String today;
    @Override
    protected void initData() {
        sr.setOnRefreshListener(ChosenFragment.this);
        today =  DateUtil.getFormatDateTime(new Date(),"yyyy-MM-dd");

        String[] split = today.split("-");
        date.setText(split[0]+"年"+split[1]+"月"+split[2]+"日");
        loadData();
    }

    private void loadData() {
        isLoading = true;
        if (HttpUtils.checkNetwork(mActivity)) {
            HttpUtils.get(Constant.KANURL+ DateUtil.getYestoday(DateUtil.getFormatDateTime(new Date(),"yyyyMMdd"),"yyyyMMdd") + "/"+title, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    String json = response.toString();
//                    System.out.print("ql--"+json);
                    PreUtils.putStringToDefault(mActivity, Constant.KANURL, json);
                    parseJson(response.toString());
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    isLoading =false;
                }
            });
        } else {
            String json = PreUtils.getStringFromDefault(mActivity, Constant.KANURL, "");
            parseJson(json);
        }

    }
    private Chosen chosenBean;
    private void parseJson(String response) {

        Gson gson = new Gson();
        chosenBean = gson.fromJson(response, Chosen.class);
        if (chosenBean.getError().contains("no result")){
            SnackbarUtils.show(mActivity,"暂无数据");
        }else{
            lv_chosen.addHeaderView(header);
            mAdapter = new ChosenItemAdapter(mActivity,chosenBean.getAnswers());
            lv_chosen.setAdapter(mAdapter);
        }

        isLoading =false;

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
        if (!isLoading ){
            loadData();
        }
        sr.setRefreshing(false);
    }
}
