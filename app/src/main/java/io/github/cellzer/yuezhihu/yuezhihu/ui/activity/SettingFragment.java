package io.github.cellzer.yuezhihu.yuezhihu.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.github.cellzer.yuezhihu.yuezhihu.R;
import io.github.cellzer.yuezhihu.yuezhihu.ui.fragment.BaseFragment;
import io.github.cellzer.yuezhihu.yuezhihu.util.DataCleanManager;
import io.github.cellzer.yuezhihu.yuezhihu.util.SnackbarUtils;

/**
 * Created by walmand_ on 2016/2/18 0018.
 */
public class SettingFragment extends BaseFragment {

    @InjectView(R.id.clean)
    FrameLayout clean;
    @InjectView(R.id.checknew)
    FrameLayout checknew;
    @InjectView(R.id.rank)
    FrameLayout rank;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_setting, null);
    }

    @OnClick(R.id.clean)
    void clean(){
        DataCleanManager.cleanApplicationData(mActivity);
        SnackbarUtils.show(mActivity,"缓存清理完成");
    }
    @OnClick(R.id.checknew)
    void checknew(){
        SnackbarUtils.show(mActivity,"正在施工中");
    }
    @OnClick(R.id.rank)
    void rank(){
        SnackbarUtils.show(mActivity,"正在施工中");
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
}
