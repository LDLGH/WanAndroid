package com.ldl.wanandroid.presenter.wx

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.wx.WXListPagerContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxUtils
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2020/1/17
 * 类说明：
 */
class WXListPagerPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<WXListPagerContract.View>(dataManager), WXListPagerContract.Presenter {

    private var mCurrentPage = 0
    private var isRefresh = true

    override fun refresh(cid: Int) {
        mCurrentPage = 0
        isRefresh = true
        getWxSumData(page = mCurrentPage, id = cid)
    }

    override fun loadMore(cid: Int) {
        mCurrentPage++
        isRefresh = false
        getWxSumData(page = mCurrentPage, id = cid)
    }

    override fun getWxSumData(id: Int, page: Int) {
        addSubscribe(
            dataManager.getWxSumData(id, page)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(object : BaseObserver<FeedArticleListData>(mView!!) {
                    override fun onNext(t: FeedArticleListData) {
                        mView?.showWxSumData(t, isRefresh)
                    }
                })
        )
    }

}