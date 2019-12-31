package com.ldl.wanandroid.presenter.wx

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.wx.WXContract
import com.ldl.wanandroid.core.DataManager
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class WXPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<WXContract.View>(dataManager), WXContract.Presenter {
}