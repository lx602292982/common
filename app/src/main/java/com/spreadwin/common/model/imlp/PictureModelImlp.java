package com.spreadwin.common.model.imlp;

import com.spreadwin.common.api.PictureService;
import com.spreadwin.common.data.HttpResult;
import com.spreadwin.common.data.PictureData;
import com.spreadwin.common.model.IPictureModel;
import com.spreadwin.common.net.NetManager;

import java.util.List;

import rx.Observable;

/**
 * Created by lixiang on 2017/2/21.
 */

public class PictureModelImlp implements IPictureModel {
    PictureService service = NetManager.getInstance().create(PictureService.class);

    @Override
    public Observable<HttpResult<List<PictureData>>> getPicturData(int count, int page, String key) {
        return service.getPictureDataInfo(count, page, key);
    }
}
