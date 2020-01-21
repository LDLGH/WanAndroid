package com.ldl.wanandroid.presenter.project

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.project.ProjectListContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxUtils
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2020/1/19
 * 类说明：
 */
class ProjectListPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<ProjectListContract.View>(dataManager), ProjectListContract.Presenter {

    private var mCurrentPage = 0
    private var isRefresh = true

    override fun refresh(cid: Int) {
        mCurrentPage = 0
        isRefresh = true
        getProjectListData(mCurrentPage, cid)
    }

    override fun loadMore(cid: Int) {
        mCurrentPage++
        isRefresh = false
        getProjectListData(mCurrentPage, cid)
    }

    override fun getProjectListData(page: Int, cid: Int) {
        addSubscribe(
            dataManager.getProjectListData(page, cid)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(object : BaseObserver<FeedArticleListData>(mView!!) {
                    override fun onNext(t: FeedArticleListData) {
                        mView?.showProjectList(t, isRefresh)
                    }
                })
        )
    }
}