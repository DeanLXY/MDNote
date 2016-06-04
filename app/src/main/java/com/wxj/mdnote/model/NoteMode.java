package com.wxj.mdnote.model;

import android.content.Context;

import com.wxj.mdnote.BaseApplication;
import com.wxj.mdnote.model.entry.Category;
import com.wxj.mdnote.model.entry.Note;
import com.wxj.mdnote.presenter.RealmDataSource;

import io.realm.Realm;

/**
 * ====================
 * 版权所有 违法必究
 *
 * @author wangx
 * @project MDNote
 * @file BaseListAdapter
 * @create_time 2016/6/4
 * @github https://github.com/wangxujie
 * @blog http://wangxujie.github.io
 */
public class NoteMode {

    //  笔记的业务类

    public void createNewNote(Note note){
        RealmDataSource dataSource = RealmDataSource.getDefault(BaseApplication.getContext());
        Realm realm = dataSource.getRealm();
        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
    }
}
