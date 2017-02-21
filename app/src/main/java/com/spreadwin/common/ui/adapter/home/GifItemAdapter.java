package com.spreadwin.common.ui.adapter.home;

import android.support.v4.app.FragmentActivity;

import com.spreadwin.common.R;
import com.spreadwin.common.data.GifData;
import com.spreadwin.common.ui.adapter.BaseAdapter;
import com.spreadwin.common.ui.adapter.ViewHolder;

import java.util.ArrayList;

/**
 * Created by lixiang on 2016/11/12.
 */

public class GifItemAdapter extends BaseAdapter<GifData> {


    public GifItemAdapter(FragmentActivity context, ArrayList<GifData> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, GifData data) {
        holder.gifload(mContext,data.getContent(),R.id.img);
        holder.setText(R.id.title, data.getTitle());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.picture_item_adapter;
    }
}
