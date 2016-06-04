package com.wxj.mdnote.view;

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
public interface INoteCreateView {

//    public void createNewNote(String subject,String content);

    // 标题信息
    public String getSubject();

    // 笔记内容信息
    public String getContent();

    public void showProgress();

    public void hideProgress();


    public void clearNoteInfo();

    // 创建完成退出
    public void finish();
}
