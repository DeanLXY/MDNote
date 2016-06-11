package com.wxj.mdnote.note.model.entity;

import com.wxj.mdnote.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private String uuid = UUID.randomUUID().toString();
    private Integer bg2;

    private String title; //title
    private String symbol;//  symbol ##  @@ %%...
    private Integer bg;

    public Category() {
    }

    public Category(String title, Integer bg, Integer bg2) {
        this.title = title;
        this.symbol = "##$1%s##";
        this.bg = bg;
        this.bg2 = bg2;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getBg() {
        return bg;
    }

    public void setBg(Integer bg) {
        this.bg = bg;
    }

    public Integer getBg2() {
        return bg2;
    }

    public void setBg2(Integer bg2) {
        this.bg2 = bg2;
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

    @Override
    public String toString() {
        return String.format(this.symbol, this.title);
    }

    public static class Default {
        public static List<Category> categories = new ArrayList<>();

        static {
            categories.add(new Category("笔记-文本", R.drawable.accountbook_shortcut_standard, R.drawable.ic_tint_cover_red));
            categories.add(new Category("笔记-图片", R.drawable.accountbook_shortcut_travel, R.drawable.ic_tint_cover_qing));
            categories.add(new Category("笔记-富媒体", R.drawable.accountbook_shortcut_benefit, R.drawable.ic_tint_cover_yellow));
            categories.add(new Category("笔记-电话号码", R.drawable.accountbook_shortcut_car, R.drawable.ic_tint_cover_green));
        }

    }
}
