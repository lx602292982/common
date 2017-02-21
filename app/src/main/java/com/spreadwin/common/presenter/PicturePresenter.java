package com.spreadwin.common.presenter;

import android.content.Context;

import com.spreadwin.common.common.view.UserView;
import com.spreadwin.common.constant.Constant;
import com.spreadwin.common.data.PictureData;
import com.spreadwin.common.model.IPictureModel;
import com.spreadwin.common.model.imlp.PictureModelImlp;
import com.spreadwin.common.rx.RxManager;
import com.spreadwin.common.rx.RxSubscriber;

import java.util.List;

/**
 * Created by lixiang on 2017/2/21.
 */

public class PicturePresenter extends BasePresenter<UserView> {

    public IPictureModel mModel;

    public PicturePresenter() {
        mModel = new PictureModelImlp();
    }

    public void getPictureData(int page, Context context) {
        mSubscription = RxManager.getInstance().doSubscribe1(mModel.getPicturData(Constant.PAGE_SIZE, page, Constant.JUhE_KEY), new RxSubscriber<List<PictureData>>(true, context) {

            @Override
            protected void _onNext(List<PictureData> pictureDatas) {
                mView.onSuccess(pictureDatas);
            }

            @Override
            protected void _onError(String s) {
                mView.onError(s);
            }
        });
    }
}
