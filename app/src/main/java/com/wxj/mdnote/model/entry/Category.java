package com.wxj.mdnote.model.entry;

import com.wxj.mdnote.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

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
    private String title; //title
    private String symbol;//  symbol ##  @@ %%...
    private Integer bg;

    public Integer getBg() {
        return bg;
    }

    public void setBg(Integer bg) {
        this.bg = bg;
    }

    public Category() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Category(String title,Integer bg) {
        this.title = title;
        this.symbol = "##$1%s##";
        this.bg = bg;
    }

    @Override
    public String toString() {
        return String.format(this.symbol,this.title);
    }

    public static class Default {
        public static List<Category> categories = new ArrayList<>();

       static  {
            categories.add(new Category("笔记-文本", R.drawable.accountbook_shortcut_standard));
            categories.add(new Category("笔记-图片", R.drawable.accountbook_shortcut_travel));
            categories.add(new Category("笔记-富媒体", R.drawable.accountbook_shortcut_benefit));
            categories.add(new Category("笔记-电话号码", R.drawable.accountbook_shortcut_car));
        }

    }
}
