package com.wxj.mdnote.model.entry;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

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
public class Category  extends BaseRealm {
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

}
