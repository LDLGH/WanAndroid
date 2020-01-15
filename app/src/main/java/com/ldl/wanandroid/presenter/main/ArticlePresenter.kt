package com.ldl.wanandroid.presenter.main

import com.blankj.utilcode.util.Utils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.main.ArticleContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxUtils
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2020/1/14
 * 类说明：
 */
class ArticlePresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<ArticleContract.View>(dataManager), ArticleContract.Presenter {

    private var mCurrentPage = 0
    private var isRefresh = true

    override fun refresh() {
        mCurrentPage = 0
        isRefresh = true
        getFeedArticleList()
    }

    override fun loadMore() {
        mCurrentPage++
        isRefresh = false
        getFeedArticleList()
    }

    override fun getFeedArticleList() {
        addSubscribe(dataManager.getFeedArticleList(mCurrentPage)
            .compose(RxUtils.rxSchedulerHelper())
            .compose(RxUtils.handleResult())
            .subscribeWith(
                object : BaseObserver<FeedArticleListData>(
                    mView!!,
                    Utils.getApp().getString(R.string.failed_to_obtain_article_list)
                ) {
                    override fun onNext(t: FeedArticleListData) {
                        mView?.showArticleList(t, isRefresh)
                    }
                }
            )
        )
    }
}