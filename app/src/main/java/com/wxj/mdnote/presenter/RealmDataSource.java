package com.wxj.mdnote.presenter;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * ====================
 * 版权所有 违法必究
 *
 * @author wangx
 * @project MDNote
 * @file BaseListAdapter
 * @create_time 2016/5/31
 * @github https://github.com/wangxujie
 * @blog http://wangxujie.github.io
 */
public class RealmDataSource {
    public void init(Context context){
        RealmConfiguration config = new RealmConfiguration.Builder(context)
                .name("")
                .build();
        Realm realm = Realm.getInstance(config);

    }
}
