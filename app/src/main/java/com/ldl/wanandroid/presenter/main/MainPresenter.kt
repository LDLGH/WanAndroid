package com.ldl.wanandroid.presenter.main

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.main.MainContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.collect.FeedArticleListData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxUtils
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/27
 * 类说明：
 */
class MainPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<MainContract.View>(dataManager), MainContract.Presenter {

    override fun getFeedArticleList(isShowError: Boolean) {
        addSubscribe(dataManager.getFeedArticleList(0)
            .compose(RxUtils.rxSchedulerHelper())
            .compose(RxUtils.handleResult())
            .subscribeWith(
                object : BaseObserver<FeedArticleListData>(
                    mView!!,
                    Utils.getApp().getString(R.string.failed_to_obtain_article_list),
                    isShowError
                ) {
                    override fun onNext(t: FeedArticleListData) {
                        LogUtils.json(t)
                    }
                }
            )
        )
    }

}