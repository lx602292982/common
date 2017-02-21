package com.spreadwin.common.api;

import com.spreadwin.common.data.HttpResult;
import com.spreadwin.common.data.JokeData;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lixiang on 2017/2/21.
 */

public interface JokeService {
    String BASE_URL = "http://api.xiaoliaoba.cn/Index/";

    /**
     * duanzi
     * @param count
     * @param page
     * @param key
     * @return
     */

    @GET("duanzi.html")
    Observable<HttpResult<List<JokeData>>> getJokeDataInfo(@Query("count") int count, @Query("page") int page, @Query("key") String key);
}
