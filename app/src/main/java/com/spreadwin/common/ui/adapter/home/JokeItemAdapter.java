package com.spreadwin.common.ui.adapter.home;

import android.support.v4.app.FragmentActivity;

import com.spreadwin.common.R;
import com.spreadwin.common.data.JokeData;
import com.spreadwin.common.ui.adapter.BaseAdapter;
import com.spreadwin.common.ui.adapter.ViewHolder;

import java.util.List;

/**
 * Created by lixiang on 2016/11/12.
 */

public class JokeItemAdapter extends BaseAdapter<JokeData> {


    public JokeItemAdapter(FragmentActivity context, List<JokeData> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, JokeData data) {
        holder.setText(R.id.des, data.getContent());
        holder.setText(R.id.title, data.getTitle());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.joke_item_adapter;
    }
}
