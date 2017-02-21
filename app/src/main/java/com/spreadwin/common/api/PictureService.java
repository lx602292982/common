package com.spreadwin.common.api;

import com.spreadwin.common.data.HttpResult;
import com.spreadwin.common.data.PictureData;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lixiang on 2017/2/21.
 */

public interface PictureService {
    String BASE_URL = "http://api.xiaoliaoba.cn/Index/";

    /**
     * duanzi
     * @param count
     * @param page
     * @param key
     * @return
     */

    @GET("tupian.html")
    Observable<HttpResult<List<PictureData>>> getPictureDataInfo(@Query("count") int count, @Query("page") int page, @Query("key") String key);

}
