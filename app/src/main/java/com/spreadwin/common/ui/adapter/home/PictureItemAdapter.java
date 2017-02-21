package com.spreadwin.common.ui.adapter.home;

import android.support.v4.app.FragmentActivity;

import com.spreadwin.common.R;
import com.spreadwin.common.data.PictureData;
import com.spreadwin.common.ui.adapter.BaseAdapter;
import com.spreadwin.common.ui.adapter.ViewHolder;

import java.util.ArrayList;

/**
 * Created by lixiang on 2016/11/12.
 */

public class PictureItemAdapter extends BaseAdapter<PictureData> {


    public PictureItemAdapter(FragmentActivity context, ArrayList<PictureData> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, PictureData data) {
        holder.load(mContext,data.getImage(),R.id.img);
        holder.setText(R.id.title, data.getTitle());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.picture_item_adapter;
    }
}
