package com.ldl.wanandroid.presenter.main

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.main.HomepageContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.collect.FeedArticleListData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxUtils
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class HomepagePresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<HomepageContract.View>(dataManager), HomepageContract.Presenter {

    override fun getFeedArticleList(isShowError: Boolean) {
        addSubscribe(dataManager.getFeedArticleList(0)
            .compose(RxUtils.rxSchedulerHelper())
            .compose(RxUtils.handleResult())
            .doOnSubscribe {
                mView?.showLoading()
            }
            .doFinally {
                mView?.showNormal()
            }
            .subscribeWith(
                object : BaseObserver<FeedArticleListData>(
                    mView!!,
                    Utils.getApp().getString(R.string.failed_to_obtain_article_list),
                    isShowError
                ) {
                    override fun onNext(t: FeedArticleListData) {
                        LogUtils.json(t)
                        mView?.showNormal()
                    }
                }
            )
        )
    }
}