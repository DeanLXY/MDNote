package com.wxj.mdnote.model.entry;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.RealmModule;

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

@RealmClass
public class Category extends RealmObject {
    @PrimaryKey
    private String categoryTitle; //title
    private String categorySymbol;//  symbol ##  @@ %%...

    public Category() {
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategorySymbol() {
        return categorySymbol;
    }

    public void setCategorySymbol(String categorySymbol) {
        this.categorySymbol = categorySymbol;
    }

    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle;
        this.categorySymbol = "##$1%s&&";
    }

    class Default {
        private List<Category> categories = new ArrayList<>();

        public Default() {
            categories.add(new Category("笔记"));
            categories.add(new Category("记录"));
            categories.add(new Category("快速回忆"));
            categories.add(new Category("电话号码"));
        }

        public List<Category> getCategories() {
            return categories;
        }
    }
}
