package com.wxj.mdnote.cpf.net;


import com.wxj.mdnote.cpf.model.CPFCityModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

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
public interface CPFAPI {
    public static final String CPF_BASE_URL = "http://v.juhe.cn";
    public static final String CPF_APP_KEY = "d0c922cd1973a37847f49d2d0ab3feaf";

//    http://v.juhe.cn/xiangji_weather/real_time_weather.php
//    http://v.juhe.cn/gerenshebao/test/getcities.php
    @GET("/gerenshebao/test/getcities.php")
    public Observable<CPFCityModel> getCities(@Query("key") String key);
}
