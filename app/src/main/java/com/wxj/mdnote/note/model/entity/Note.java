package com.wxj.mdnote.note.model.entity;

import com.wxj.mdnote.utils.DateUtils;

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
 * @create_time 2016/6/4
 * @github https://github.com/wangxujie
 * @blog http://wangxujie.github.io
 */
@RealmClass
public class Note extends RealmObject {
    // 创建  || 显示笔记的实体类
    @PrimaryKey
    private String uuid = UUID.randomUUID().toString();

    private String subject;
    private String content;
    private String createTime = DateUtils.getCurrentTime(); //创建时间
    private String lastModifyTime;//最后修改时间


    public Integer iconCover;//封面
    public String categoryName; //分类

    public Integer getIconCover() {
        return iconCover;
    }

    public void setIconCover(Integer iconCover) {
        this.iconCover = iconCover;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
