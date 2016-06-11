package com.wxj.mdnote.note.model;

import com.wxj.mdnote.note.model.entity.Category;

import java.util.List;

/**
 * ====================
 * 版权所有 违法必究
 *
 * @author wangx
 * @project MDNote
 * @file BaseListAdapter
 * @create_time 2016/5/31
 * @github https://github.com/wangxujie
 * @blog http://wangxujie.github.io
 */
public interface ICategoryModel {
    void insert(Category category);

    List<Category> findAll();

    void update(Category category);

    void delete(Category category);

}
