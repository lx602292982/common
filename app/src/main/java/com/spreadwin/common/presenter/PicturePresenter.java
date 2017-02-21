package com.spreadwin.common.presenter;

import android.content.Context;

import com.spreadwin.common.common.view.UserView;
import com.spreadwin.common.data.JokeData;
import com.spreadwin.common.data.PictureData;
import com.spreadwin.common.model.IJoKeModel;
import com.spreadwin.common.model.IPictureModel;
import com.spreadwin.common.model.imlp.JoKeModelImlp;
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

    public void getJokeData(int count, int page, String key, Context context) {
        mSubscription = RxManager.getInstance().doSubscribe1(mModel.getPicturData(count, page, key), new RxSubscriber<List<PictureData>>(true, context) {

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
