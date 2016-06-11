package com.wxj.greendaolib;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * ====================
 * 版权所有 违法必究
 *
 * @author wangx
 * @project MDNote
 * @file BaseListAdapter
 * @create_time 2016/6/11
 * @github https://github.com/wangxujie
 * @blog http://wangxujie.github.io
 * ====================
 */
public class MainGenerater {
    public static void main(String[] args) {

        Schema schema = new Schema(1, "com.wxj.greendaolib.model");
        Entity cityCPF = schema.addEntity("CityCPF");//实体类

        cityCPF.addIdProperty();// id
        cityCPF.addStringProperty("city");
        cityCPF.addStringProperty("cname");
        cityCPF.addStringProperty("month");
        cityCPF.addStringProperty("pname");
        cityCPF.addStringProperty("prov");
        cityCPF.addStringProperty("region");
        cityCPF.addStringProperty("rule");
        cityCPF.addStringProperty("subdl");
        cityCPF.addStringProperty("timestamp");

        try {
            new DaoGenerator().generateAll(schema,"./greendaolib/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
