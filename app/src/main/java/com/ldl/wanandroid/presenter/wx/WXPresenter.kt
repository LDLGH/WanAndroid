package com.ldl.wanandroid.presenter.wx

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.wx.WXContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.wx.WxAuthor
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxUtils
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class WXPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<WXContract.View>(dataManager), WXContract.Presenter {

    override fun getWxAuthorListData() {
        addSubscribe(
            dataManager.getWxAuthorListData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .doOnSubscribe {
                    mView?.showLoading()
                }
                .doFinally {
                    mView?.showNormal()
                }
                .subscribeWith(object : BaseObserver<List<WxAuthor>>(mView!!) {
                    override fun onNext(t: List<WxAuthor>) {
                        mView?.showWxAuthorList(t)
                    }
                })
        )
    }

}