package com.spreadwin.common.model.imlp;

import com.spreadwin.common.api.JokeService;
import com.spreadwin.common.data.HttpResult;
import com.spreadwin.common.data.JokeData;
import com.spreadwin.common.model.IJoKeModel;
import com.spreadwin.common.net.NetManager;

import java.util.List;

import rx.Observable;

/**
 * Created by lixiang on 2017/2/21.
 */

public class JoKeModelImlp implements IJoKeModel {

    JokeService service = NetManager.getInstance().create(JokeService.class);

    @Override
    public Observable<HttpResult<List<JokeData>>> getJoKeData(int count, int page, String key) {
        return service.getJokeDataInfo(count, page, key);
    }
}
