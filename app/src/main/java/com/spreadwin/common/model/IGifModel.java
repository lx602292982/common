package com.spreadwin.common.model;

import com.spreadwin.common.data.GifData;
import com.spreadwin.common.data.HttpResult;
import com.spreadwin.common.data.JokeData;
import com.spreadwin.common.rx.RxSubscriber;

import java.util.List;

import rx.Observable;

/**
 * Created by lixiang on 2017/2/21.
 */

public interface IGifModel {

    Observable<HttpResult<List<GifData>>> getGifData(int count, int page, String key);

}
