package com.spreadwin.common.rx;


import android.content.Intent;

import com.spreadwin.common.constant.Constant;
import com.spreadwin.common.data.HttpResult;
import com.spreadwin.common.net.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author: Othershe
 * Time:  2016/8/11 17:53
 */
public class RxManager {

    private RxManager() {
    }


    public static RxManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RxManager INSTANCE = new RxManager();
    }

    public <T> Subscription doSubscribe(Observable<HttpResult> observable, Subscriber<T> subscriber) {
        return observable
                .map(new Func1<HttpResult, T>() {
                    @Override
                    public T call(HttpResult httpResult) {
                        if (httpResult.getError_code().equals(Constant.FAULT)) {
                            throw new ApiException(httpResult.getReason());
                        } else if (httpResult.getError_code().equals(Constant.NEW_LOGIN)) {
                            throw new ApiException(httpResult.getReason());
                        }
                        return (T) (httpResult.getResult() == null ? httpResult.getReason() : httpResult.getResult());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public <T> Subscription doSubscribe1(Observable<HttpResult<T>> observable, Subscriber<T> subscriber) {
        return observable
                .map(new Func1<HttpResult<T>, T>() {
                    @Override
                    public T call(HttpResult<T> httpResult) {
                        if (httpResult.getError_code().equals(Constant.FAULT) ) {
                            throw new ApiException(httpResult.getReason());
                        }if (httpResult.getError_code().equals(Constant.NEW_LOGIN)) {
                            throw new ApiException(httpResult.getReason());
                        }
                        return httpResult.getResult();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
