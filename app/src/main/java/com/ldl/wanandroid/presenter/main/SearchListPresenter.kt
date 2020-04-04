package com.ldl.wanandroid.presenter.main

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.main.SearchListContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxUtils
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2020/3/16
 * 类说明：
 */
class SearchListPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<SearchListContract.View>(dataManager), SearchListContract.Presenter {


    private var mCurrentPage = 0
    private var isRefresh = true

    override fun refresh(k: String?) {
        mCurrentPage = 0
        isRefresh = true
        getSearchList(k)
    }

    override fun loadMore(k: String?) {
        mCurrentPage++
        isRefresh = false
        getSearchList(k)
    }

    override fun getSearchList(k: String?) {
        addSubscribe(dataManager.getSearchList(mCurrentPage, k)
            .compose(RxUtils.rxSchedulerHelper())
            .compose(RxUtils.handleResult())
            .subscribeWith(object : BaseObserver<FeedArticleListData>(mView!!) {

                override fun onNext(t: FeedArticleListData) {
                    mView?.showSearchList(t, isRefresh)
                }
            }
            )
        )
    }
}