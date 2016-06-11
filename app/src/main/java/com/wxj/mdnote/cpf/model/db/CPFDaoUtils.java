package com.wxj.mdnote.cpf.model.db;

import android.database.sqlite.SQLiteDatabase;

import com.wxj.cpfapp.CityCPFDao;
import com.wxj.cpfapp.DaoMaster;
import com.wxj.cpfapp.DaoSession;
import com.wxj.mdnote.BaseApplication;

/**
 * ====================
 * 版权所有 违法必究
 *
 * @author wangx
 * @project MDNote
 * @file BaseListAdapter
 * @create_time 2016/6/11
 * @github https://github.com/wangxujie
 * @blog http://wangxujie.github.io
 * ====================
 */
public class CPFDaoUtils {

    private static DaoSession newSession;

    public static CityCPFDao getCityCPFDao() {
        if (newSession == null) {
            init();
        }
        return newSession.getCityCPFDao();
    }

    private static void init() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getContext(), "cpf.db", null);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();

        DaoMaster master = new DaoMaster(db);
        newSession = master.newSession();
    }

    public static void clear() {
        if (newSession != null) {
            newSession.clear();
        }
    }
}
