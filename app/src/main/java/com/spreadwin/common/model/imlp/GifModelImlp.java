package com.spreadwin.common.model.imlp;

import com.spreadwin.common.api.GifJokeService;
import com.spreadwin.common.data.GifData;
import com.spreadwin.common.data.HttpResult;
import com.spreadwin.common.data.JokeData;
import com.spreadwin.common.model.IGifModel;
import com.spreadwin.common.net.NetManager;

import java.util.List;

import rx.Observable;

/**
 * Created by lixiang on 2017/2/21.
 */

public class GifModelImlp implements IGifModel {
    GifJokeService service = NetManager.getInstance().create(GifJokeService.class);

    @Override
    public Observable<HttpResult<List<GifData>>> getGifData(int count, int page, String key) {
        return service.getGifDataInfo(count, page, key);
    }
}
