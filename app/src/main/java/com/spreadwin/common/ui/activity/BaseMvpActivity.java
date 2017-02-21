package com.spreadwin.common.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.spreadwin.common.common.view.backactivity.BackBaseActivity;
import com.spreadwin.common.presenter.BasePresenter;


/**
 * Author: Othershe
 * Time:  2016/8/11 11:14
 */
public abstract class BaseMvpActivity<V, P extends BasePresenter<V>> extends BackBaseActivity {
    protected P mPresenter;

    protected abstract P initPresenter();

    protected abstract void fetchData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attach((V) this);

        fetchData();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}