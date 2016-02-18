package io.github.cellzer.yuezhihu.yuezhihu.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.cellzer.yuezhihu.yuezhihu.R;
import io.github.cellzer.yuezhihu.yuezhihu.ui.fragment.BaseFragment;

/**
 * Created by walmand_ on 2016/2/18 0018.
 */
public class AboutFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_about,null);
    }
}
