package com.wxj.mdnote.note.presenter;

import com.wxj.mdnote.note.model.NoteModel;
import com.wxj.mdnote.note.model.entity.Note;
import com.wxj.mdnote.note.view.INoteCreateView;

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
public class NoteCreatePresenter {
    INoteCreateView view;
    private final NoteModel noteMode;

    public NoteCreatePresenter(INoteCreateView view) {
        this.view = view;
        noteMode = new NoteModel();
    }

    public void createNewNote(){
        Note note = new Note();
        note.setSubject(view.getSubject());
        note.setContent(view.getContent());
        note.setIconCover(view.getCategory().getBg2());
        note.setCategoryName(view.getCategory().getTitle());
        noteMode.createNewNote(note);
        view.finish();
    }
}
