package com.ldl.wanandroid.presenter.knowledge

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.knowledge.KnowledgeDetailPagerContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxUtils
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2020/1/16
 * 类说明：
 */
class KnowledgeDetailPagerPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<KnowledgeDetailPagerContract.View>(dataManager),
    KnowledgeDetailPagerContract.Presenter {

    private var mCurrentPage = 0
    private var isRefresh = true

    override fun refresh(cid: Int) {
        mCurrentPage = 0
        isRefresh = true
        getKnowledgeHierarchyDetailData(page = mCurrentPage, cid = cid)
    }

    override fun loadMore(cid: Int) {
        mCurrentPage++
        isRefresh = false
        getKnowledgeHierarchyDetailData(page = mCurrentPage, cid = cid)
    }

    override fun getKnowledgeHierarchyDetailData(page: Int, cid: Int) {
        addSubscribe(
            dataManager.getKnowledgeHierarchyDetailData(page, cid)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(object : BaseObserver<FeedArticleListData>(mView!!) {
                    override fun onNext(t: FeedArticleListData) {
                        mView?.showKnowledgeHierarchyDetailList(t, isRefresh)
                    }
                })
        )
    }


}