package com.wxj.mdnote.cpf.view;

import com.wxj.mdnote.cpf.model.Result;

import java.util.List;

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
public interface ICityListView {
    public void showPrompt();
    public void hidePrompt();


    public void showErrorView();
    public void hideErrorVIew();

    public void showCities(List<Result> cities);
}
