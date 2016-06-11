package com.wxj.mdnote.note.presenter;

import com.wxj.mdnote.note.model.NoteModel;
import com.wxj.mdnote.note.model.OnRealmChangeListener;
import com.wxj.mdnote.note.model.entity.Note;
import com.wxj.mdnote.note.view.INoteListView;

import java.util.List;

/**
 * ====================
 * 版权所有 违法必究
 *
 * @author wangx
 * @project MDNote
 * @file BaseListAdapter
 * @create_time 2016/6/3
 * @github https://github.com/wangxujie
 * @blog http://wangxujie.github.io
 */
public class NoteListPresenter {
    private final NoteModel mode;
    private INoteListView view;

    public NoteListPresenter(INoteListView view) {
        this.view = view;
        mode = new NoteModel();
    }


    public List<Note> findAll() {
        return mode.findAll(new OnRealmChangeListener<List<Note>>() {
            @Override
            public void onChange(List<Note> notes) {
                view.notifyDataSetChange();
            }
        });
    }

    public void clear() {
        mode.clear();
    }


    public void login() {
        view.requestUserInfo(null);
    }

    public void createNewCategory() {
        view.createNewCategory();
    }

    public void createNewNote() {
        view.createNewNote();
    }

}
