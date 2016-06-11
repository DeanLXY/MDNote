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
public interface CPFCityAPI {
//    http://v.juhe.cn/xiangji_weather/real_time_weather.php
//    http://v.juhe.cn/gerenshebao/test/getcities.php
    @GET("/gerenshebao/test/getcities.php")
    public Observable<CPFCityModel> getCities(@Query("dtype") String jsonOrXml , @Query("key") String key);
}
