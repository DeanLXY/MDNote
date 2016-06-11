package com.wxj.mdnote.note.model;

import com.wxj.mdnote.BaseApplication;
import com.wxj.mdnote.note.model.entity.Note;
import com.wxj.mdnote.note.presenter.RealmDataSource;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

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
public class NoteModel {

    //  笔记的业务类

    public void createNewNote(Note note) {
        RealmDataSource dataSource = RealmDataSource.getDefault(BaseApplication.getContext());
        Realm realm = dataSource.getRealm();
        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
    }

    /**
     * 查询所有笔记信息
     *
     * @return
     */
    public RealmResults<Note> findAll(final OnRealmChangeListener<List<Note>> changeListener) {
        RealmDataSource dataSource = RealmDataSource.getDefault(BaseApplication.getContext());
        Realm realm = dataSource.getRealm();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm element) {
                if (changeListener != null) {
                    changeListener.onChange(null);
                }
            }
        });
        RealmQuery<Note> realmQuery = realm.where(Note.class);
        return realmQuery.findAllSorted("createTime", Sort.DESCENDING);

    }


    public void clear() {
        RealmDataSource dataSource = RealmDataSource.getDefault(BaseApplication.getContext());
        Realm realm = dataSource.getRealm();
        realm.removeAllChangeListeners();
        realm.close();
    }
}
