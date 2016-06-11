package com.wxj.mdnote.note.presenter;

import android.content.Context;

import com.wxj.mdnote.IConstant;

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
// Model: realm operate object
public class RealmDataSource {
    private static RealmDataSource DEFAULT;
    private final Realm realm;

    private RealmDataSource(Context context) {
        RealmConfiguration config = new RealmConfiguration.Builder(context)
                .name(IConstant.REALM_DB)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
    }

    public static synchronized RealmDataSource getDefault(Context context) {
        if (DEFAULT == null) {
            DEFAULT = new RealmDataSource(context);
        }
        return DEFAULT;
    }

    public Realm getRealm() {
        return realm;
    }


}
