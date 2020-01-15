package com.ldl.wanandroid.presenter.main

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.main.WebViewContract
import com.ldl.wanandroid.core.DataManager
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2020/1/14
 * 类说明：
 */
class WebViewPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<WebViewContract.View>(dataManager), WebViewContract.Presenter {
    
}