package com.wxj.mdnote.cpf.model;

import com.wxj.mdnote.RetrofitUtil;
import com.wxj.mdnote.cpf.net.CPFAPI;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
public class CPFModel {

    public Observable<CPFCityModel> getCities() {

//        QueryBuilder<CityCPF> cityCPFQueryBuilder = CPFDaoUtils.getCityCPFDao().queryBuilder();
//        Query<CityCPF> build = cityCPFQueryBuilder.build();
//        List<CityCPF> list = build.list();
//        if (list != null && list.size() != 0) {
//            return Observable.just(list);
//        } else {
        Retrofit retrofit = RetrofitUtil.getRetrofit(CPFAPI.CPF_BASE_URL);
        final CPFAPI CPFAPI = retrofit.create(CPFAPI.class);
        return CPFAPI.getCities(CPFAPI.CPF_APP_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
//        }


    }
}
