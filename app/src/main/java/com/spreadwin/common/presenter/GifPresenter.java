package com.spreadwin.common.presenter;

import android.content.Context;

import com.spreadwin.common.common.view.UserView;
import com.spreadwin.common.constant.Constant;
import com.spreadwin.common.data.GifData;
import com.spreadwin.common.data.HttpResult;
import com.spreadwin.common.data.JokeData;
import com.spreadwin.common.model.IGifModel;
import com.spreadwin.common.model.IJoKeModel;
import com.spreadwin.common.model.imlp.GifModelImlp;
import com.spreadwin.common.model.imlp.JoKeModelImlp;
import com.spreadwin.common.rx.RxManager;
import com.spreadwin.common.rx.RxSubscriber;

import java.util.List;

/**
 * Created by lixiang on 2017/2/21.
 */

public class GifPresenter extends BasePresenter<UserView> {

    public IGifModel mModel;

    public GifPresenter() {
        mModel = new GifModelImlp();
    }

    public void getGifData(int page, Context context) {
        mSubscription = RxManager.getInstance().doSubscribe1(mModel.getGifData(Constant.PAGE_SIZE, page, Constant.JUhE_KEY), new RxSubscriber<List<GifData>>(false,context) {

            @Override
            protected void _onNext(List<GifData> gifDatas) {
                  mView.onSuccess(gifDatas);
            }

            @Override
            protected void _onError(String error) {
               mView.onError(error);
            }
        });
    }
}
