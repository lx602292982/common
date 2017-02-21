package com.spreadwin.common.presenter;

import android.content.Context;

import com.spreadwin.common.common.view.UserView;
import com.spreadwin.common.constant.Constant;
import com.spreadwin.common.data.JokeData;
import com.spreadwin.common.model.IJoKeModel;
import com.spreadwin.common.model.imlp.JoKeModelImlp;
import com.spreadwin.common.rx.RxManager;
import com.spreadwin.common.rx.RxSubscriber;

import java.util.List;

/**
 * Created by lixiang on 2017/2/21.
 */

public class JoKePresenter extends BasePresenter<UserView> {

    public IJoKeModel mModel;

    public JoKePresenter() {
        mModel = new JoKeModelImlp();
    }

    public void getJokeData(int page, Context context) {
        mSubscription = RxManager.getInstance().doSubscribe1(mModel.getJoKeData(Constant.PAGE_SIZE, page, Constant.JUhE_KEY), new RxSubscriber<List<JokeData>>(false, context) {
            @Override
            protected void _onNext(List<JokeData> jokeDatas) {
                mView.onSuccess(jokeDatas);
            }

            @Override
            protected void _onError(String s) {
                mView.onError(s);
            }
        });
    }
}
