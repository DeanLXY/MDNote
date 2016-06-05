package com.wxj.mdnote.model;

import com.wxj.mdnote.BaseApplication;
import com.wxj.mdnote.model.entry.Category;
import com.wxj.mdnote.model.entry.Note;
import com.wxj.mdnote.presenter.RealmDataSource;
import com.wxj.mdnote.utils.LogUtils;

import java.util.ArrayList;
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
        Category category = note.getCategory();
        category.setUuid(note.getUuid());
        realm.copyToRealm(category);
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
                LogUtils.d("%s", ">>>>>>>>>>>>onChange<<<<<<<<<<<<");
                if (changeListener != null) {
                    // remove
                  /*  RealmQuery<Note> realmQuery = element.where(Note.class);
                    RealmResults<Note> all = realmQuery.findAllSorted("createTime", Sort.DESCENDING);
                    List<Note> notes = null;
                    if (all == null || all.size() == 0) {
                        notes = new ArrayList<>();
                    } else {
                        notes = all.subList(0, all.size() - 1);
                    }*/
                    changeListener.onChange(null);
                }
            }
        });
        RealmQuery<Note> realmQuery = realm.where(Note.class);
        RealmResults<Note> all = realmQuery.findAllSorted("createTime", Sort.DESCENDING);
        Note note;
        for (int i = 0; i < all.size(); i++) {
            note = all.get(i);
            RealmQuery<Category> categoryRealmQuery = realm.where(Category.class).equalTo("uuid", note.getUuid());
            Category category = categoryRealmQuery.findFirst();
            note.setCategory(category);
        }
        return all;
    }


    public void clear() {
        RealmDataSource dataSource = RealmDataSource.getDefault(BaseApplication.getContext());
        Realm realm = dataSource.getRealm();
        realm.removeAllChangeListeners();
        realm.close();
    }
}
