package com.spreadwin.common.model;

import com.spreadwin.common.data.HttpResult;
import com.spreadwin.common.data.JokeData;
import com.spreadwin.common.data.PictureData;

import java.util.List;

import rx.Observable;

/**
 * Created by lixiang on 2017/2/21.
 */

public interface IPictureModel {

    Observable<HttpResult<List<PictureData>>> getPicturData(int count, int page, String key);
}
