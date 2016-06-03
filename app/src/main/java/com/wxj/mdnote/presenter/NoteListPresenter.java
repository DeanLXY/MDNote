package com.wxj.mdnote.presenter;

import com.wxj.mdnote.view.INoteListView;

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

    public NoteListPresenter(INoteListView view) {
        this.view = view;
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
