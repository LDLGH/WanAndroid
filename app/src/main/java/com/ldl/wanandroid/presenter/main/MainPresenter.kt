package com.ldl.wanandroid.presenter.main

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.main.MainContract
import com.ldl.wanandroid.core.DataManager
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/27
 * 类说明：
 */
class MainPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<MainContract.View>(dataManager), MainContract.Presenter {
}