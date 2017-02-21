package com.spreadwin.common.ui.fragment.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.spreadwin.common.R;
import com.spreadwin.common.common.view.UserView;
import com.spreadwin.common.data.JokeData;
import com.spreadwin.common.presenter.JoKePresenter;
import com.spreadwin.common.ui.adapter.OnItemClickListeners;
import com.spreadwin.common.ui.adapter.OnLoadMoreListener;
import com.spreadwin.common.ui.adapter.ViewHolder;
import com.spreadwin.common.ui.adapter.home.JokeItemAdapter;
import com.spreadwin.common.ui.fragment.BaseMvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by lixiang on 2017/2/21.
 */

public class JoKeFragment extends BaseMvpFragment<UserView, JoKePresenter> implements UserView, OnItemClickListeners<JokeData>
        , OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String ARG_POSITION = "position";

    private static final String TAG = "MainActivity";

    @BindView(R.id.type_item_recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.type_item_swipfreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private JokeItemAdapter mJokeItemAdapter;

    private int PAGE_COUNT = 1;

    private int mTempPageCount = 2;

    private boolean isLoadMore;

    @Override
    protected JoKePresenter initPresenter() {
        return new JoKePresenter();
    }

    public static JoKeFragment newInstance(int position) {
        JoKeFragment f = new JoKeFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    protected void fetchData() {
        mPresenter.getJokeData(PAGE_COUNT, getActivity());
    }

    @Override
    protected int initLayoutId() {
        return R.layout.framgent_joke;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(getActivity());
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //实现首次自动显示加载提示
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        //上拉加载
        mJokeItemAdapter = new JokeItemAdapter(getActivity(), new ArrayList<JokeData>(), true);
        mJokeItemAdapter.setLoadingView(R.layout.load_loading_layout);
        mJokeItemAdapter.setOnLoadMoreListener(this);
        mJokeItemAdapter.setOnItemClickListener(this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mJokeItemAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(Object data) {
        List<JokeData> jokeData = (List<JokeData>) data;
        mJokeItemAdapter.setData(jokeData);
        if (isLoadMore) {
            if (mJokeItemAdapter.getItemCount() == 0) {
                mJokeItemAdapter.setLoadEndView(R.layout.load_end_layout);
            } else {
                mJokeItemAdapter.setLoadMoreData(jokeData);
                mTempPageCount++;
            }
        } else {
            mJokeItemAdapter.setNewData(jokeData);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
        PAGE_COUNT = 1;
        fetchData();
    }


    @Override
    public void onLoadMore(boolean isReload) {
        if (PAGE_COUNT == mTempPageCount && !isReload) {
            return;
        }
        isLoadMore = true;
        PAGE_COUNT = mTempPageCount;
        fetchData();
    }

    @Override
    public void onItemClick(ViewHolder viewHolder, JokeData data, int position) {

    }

    @Override
    public void onError(String error) {
        Log.d(TAG, error);
        if (isLoadMore) {
            mJokeItemAdapter.setLoadFailedView(R.layout.load_failed_layout);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
