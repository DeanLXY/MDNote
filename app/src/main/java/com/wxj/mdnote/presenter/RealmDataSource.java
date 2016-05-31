package com.wxj.mdnote.presenter;

import android.content.Context;

import com.wxj.mdnote.IConstant;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

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
public class RealmDataSource<Model extends RealmObject> {
    private RealmDataSource() {
    }

    private static final RealmDataSource DEFAULT = new RealmDataSource();

    public static RealmDataSource getDefault() {
        return DEFAULT;
    }

    public class Builder {
        private Context context;
        private final Realm realm;

        public Builder(Context context) {
            this.context = context;
            RealmConfiguration config = new RealmConfiguration.Builder(context)
                    .name(IConstant.REALM_DB)
                    .build();
            realm = Realm.getInstance(config);
        }

        public RealmDataSource build() {
            return RealmDataSource.getDefault();
        }


        /**
         * C
         */
        public Builder insert(Model model) {
            realm.beginTransaction();
            realm.copyToRealm(model);
            realm.commitTransaction();
            return this;
        }

        /**
         * D
         */
        public Builder delete(Model model) {
            realm.where(model.getClass()).equalTo("", "").findFirst();
            realm.beginTransaction();
//            realm.delete(model.getClass());
            model.deleteFromRealm();
            realm.commitTransaction();
            return this;
        }


    }
}
