package com.wxj.mdnote.cpf;

import com.wxj.cpfapp.CityCPF;
import com.wxj.cpfapp.CityCPFDao;
import com.wxj.mdnote.cpf.model.CPFCityModel;
import com.wxj.mdnote.cpf.model.CPFModel;
import com.wxj.mdnote.cpf.model.Result;
import com.wxj.mdnote.cpf.model.db.CPFDaoUtils;
import com.wxj.mdnote.cpf.view.ICityListView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
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
public class CitySuppoertPresenter {


    private final CPFModel model;
    private ICityListView view;

    public CitySuppoertPresenter(ICityListView view) {
        this.view = view;
        model = new CPFModel();
    }

    public void getCities() {
        model.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        view.showPrompt();
                    }
                })
                .map(new Func1<CPFCityModel, List<Result>>() {
                    @Override
                    public List<Result> call(CPFCityModel cpfCityModel) {
                        Collections.sort(cpfCityModel.getResult(), new Comparator<Result>() {
                            @Override
                            public int compare(Result lhs, Result rhs) {
                                return lhs.getCity().compareTo(rhs.getCity());
                            }
                        });
                        return cpfCityModel.getResult();
                    }
                })
                .subscribe(new Subscriber<List<Result>>() {
                    @Override
                    public void onCompleted() {
                        view.hidePrompt();
                        view.hideErrorVIew();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showErrorView();
                    }

                    @Override
                    public void onNext(List<Result> results) {
                        view.showCities(results);
                        cacheResult(results);
                    }
                });
    }

    private void cacheResult(List<Result> results) {
        Observable.from(results)
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Result result) {
                        CityCPFDao cityCPFDao = CPFDaoUtils.getCityCPFDao();
                        cityCPFDao.insert(new CityCPF(null, result.getCity(), result.getCname(), result.getMonth(),
                                result.getPname(), result.getProv(), result.getRegion(), result.getRule() + ""
                                , result.getSubdl() + "", result.getTimestamp() + ""));
                    }
                });
    }
}
