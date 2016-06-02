package com.wxj.mdnote.view;

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
public interface INoteListView {
    // 管理主界面所有功能  统一管理
    //1. 创建 新分类
    public void createNewCategory(String title,String des,String path);

    //2 搜索  //TODO 最后添加吧
    public void onStartSearch(String keyword);
    // 3.登录 -->暂定为获取用户 邮箱信息(同步使用)
    //4.创建新笔记
    public void createNewNote();

    //
}
