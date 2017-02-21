package com.spreadwin.common.net;


import com.spreadwin.common.MyApplication;
import com.spreadwin.common.api.JokeService;
import com.spreadwin.common.common.utils.AppUtils;
import com.spreadwin.common.common.utils.CookieManger;
import com.spreadwin.common.common.utils.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author: Othershe
 * Time:  2016/8/11 14:30
 */
public class NetManager {
    private static final int DEFAULT_TIMEOUT = 10;

    public static NetManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final NetManager INSTANCE = new NetManager();
    }

    private NetManager() {

    }

    public <S> S create(Class<S> service) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(getBaseUrl(service))
                .build();
        return retrofit.create(service);
    }

    public <S> S create1(Class<S> service) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(getBaseUrl(service))
                .build();
        return retrofit.create(service);
    }

    /**
     * 解析接口中的BASE_URL，解决BASE_URL不一致的问题
     *
     * @param service
     * @param <S>
     * @return
     */
    private <S> String getBaseUrl(Class<S> service) {

        try {
            Field field = service.getField("BASE_URL");
            return (String) field.get(service);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private OkHttpClient getOkHttpClient() {
        //配置超时拦截器
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.cookieJar(new CookieManger(MyApplication.getContext()));

        File httpCacheDirectory = new File(MyApplication.getContext().getExternalCacheDir(), "responses");
        builder.cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024));
        builder.addInterceptor(interceptor);
        
        //配置log打印拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!AppUtils.isNetworkReachable(MyApplication.getContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .url(JokeService.BASE_URL).build();
                ToastUtil.show(MyApplication.getContext(), "暂无网络");//子线程安全显示Toast
            }

            Response response = chain.proceed(request);
            if (AppUtils.isNetworkReachable(MyApplication.getContext())) {
                int maxAge = 60 * 60; // read from cache for 1 minute
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    };


}
