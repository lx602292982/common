package com.spreadwin.common.ui.fragment.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.spreadwin.common.R;
import com.spreadwin.common.common.view.UserView;
import com.spreadwin.common.data.GifData;
import com.spreadwin.common.presenter.GifPresenter;
import com.spreadwin.common.ui.adapter.OnLoadMoreListener;
import com.spreadwin.common.ui.adapter.home.GifItemAdapter;
import com.spreadwin.common.ui.fragment.BaseMvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lixiang on 2017/2/21.
 */

public class GifFragment extends BaseMvpFragment<UserView, GifPresenter> implements UserView
        , OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String ARG_POSITION = "position";

    private static final String TAG = "MainActivity";

    @InjectView(R.id.type_item_recyclerview)
    RecyclerView mRecyclerView;

    @InjectView(R.id.type_item_swipfreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private GifItemAdapter mGifItemAdapter;

    private int PAGE_COUNT = 1;

    private int mTempPageCount = 2;

    private boolean isLoadMore;

    @Override
    protected GifPresenter initPresenter() {
        return new GifPresenter();
    }

    public static GifFragment newInstance(int position) {
        GifFragment f = new GifFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    protected void fetchData() {
        mPresenter.getGifData(PAGE_COUNT, getActivity());
    }

    @Override
    protected int initLayoutId() {
        return R.layout.framgent_joke;
    }

    @Override
    protected void initView() {
        ButterKnife.inject(getActivity());
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
//        //实现首次自动显示加载提示
//        mSwipeRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mSwipeRefreshLayout.setRefreshing(true);
//            }
//        });

        //上拉加载
        mGifItemAdapter = new GifItemAdapter(getActivity(), new ArrayList<GifData>(), true);
        mGifItemAdapter.setLoadingView(R.layout.load_loading_layout);
        mGifItemAdapter.setOnLoadMoreListener(this);


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mGifItemAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(Object data) {
        List<GifData> pictureData = (List<GifData>) data;
        mGifItemAdapter.setData(pictureData);
        if (isLoadMore) {
            if (pictureData.size() == 0) {
                mGifItemAdapter.setLoadEndView(R.layout.load_end_layout);
            } else {
                mGifItemAdapter.setLoadMoreData(pictureData);
                mTempPageCount++;
            }
        } else {
            mGifItemAdapter.setNewData(pictureData);
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
    public void onError(String error) {
        Log.d(TAG, error);
        if (isLoadMore) {
            mGifItemAdapter.setLoadFailedView(R.layout.load_failed_layout);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(getActivity());
    }


}
