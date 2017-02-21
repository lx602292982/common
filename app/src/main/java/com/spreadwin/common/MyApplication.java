package com.spreadwin.common;

import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initRealm();

    }

    public static Context getContext() {
        return mContext;
    }

    private void initRealm() {
//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
//        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
