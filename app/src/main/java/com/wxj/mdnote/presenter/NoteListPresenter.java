package com.wxj.mdnote.presenter;

import com.wxj.mdnote.model.NoteMode;
import com.wxj.mdnote.model.OnRealmChangeListener;
import com.wxj.mdnote.model.entry.Note;
import com.wxj.mdnote.view.INoteListView;

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
    private INoteListView view;
    private final NoteMode mode;

    public NoteListPresenter(INoteListView view) {
        this.view = view;
        mode = new NoteMode();
    }


    public List<Note> findAll(){
        return mode.findAll(new OnRealmChangeListener<List<Note>>() {
            @Override
            public void onChange(List<Note> notes) {
                view.notifyDataSetChange();
            }
        });
    }

    public void clear(){
        mode.clear();
    }




    public void login() {
        view.requestUserInfo(null);
    }

    public void createNewCategory(){
        view.createNewCategory();
    }

    public void createNewNote(){
        view.createNewNote();
    }

}
