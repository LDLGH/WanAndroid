package com.ldl.wanandroid.presenter.main

import com.blankj.utilcode.util.Utils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.main.HomepageContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.BaseResponse
import com.ldl.wanandroid.core.bean.main.ZipMainData
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxUtils
import io.reactivex.Observable
import io.reactivex.functions.Function3
import java.util.*
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class HomepagePresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<HomepageContract.View>(dataManager), HomepageContract.Presenter {

    private var mCurrentPage = 0
    private var isRefresh = true

    override fun refresh() {
        mCurrentPage = 0
        getAllData(false)
    }

    override fun loadMore() {
        mCurrentPage++
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

    override fun getBannerData() {
        addSubscribe(
            dataManager.getBannerData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(
                    object : BaseObserver<List<BannerData>>(
                        mView!!
                    ) {
                        override fun onNext(t: List<BannerData>) {
                            mView?.showBanner(t)
                        }
                    }
                )
        )
    }

    override fun getTopTopSearchData() {
        addSubscribe(
            dataManager.getTopSearchData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(object : BaseObserver<List<TopSearchData>>(mView!!) {
                    override fun onNext(t: List<TopSearchData>) {
                        mView?.showTopTopSearch(t)
                    }
                })
        )
    }

    override fun getAllData(isShowLoading: Boolean) {
        isRefresh = true
        val observable = dataManager.getTopSearchData()
        val observable1 = dataManager.getBannerData()
        val observable2 = dataManager.getFeedArticleList(0)
        addSubscribe(
            Observable.zip(observable, observable1, observable2,
                Function3<BaseResponse<List<TopSearchData>>, BaseResponse<List<BannerData>>, BaseResponse<FeedArticleListData>, ZipMainData> { t1, t2, t3 ->
                    ZipMainData(t1, t2, t3)
                }).compose(RxUtils.rxSchedulerHelper())
                .doOnSubscribe {
                    if (isShowLoading) {
                        mView?.showLoading()
                    }
                }
                .doFinally {
                    if (isShowLoading) {
                        mView?.showNormal()
                    }
                }
                .subscribeWith(object : BaseObserver<ZipMainData>(mView!!) {
                    override fun onNext(t: ZipMainData) {
                        mView?.showTopTopSearch(t.topSearchBaseResponse.data)
                        mView?.showBanner(t.bannerResponse.data)
                        mView?.showArticleList(t.feedArticleListResponse.data, isRefresh)
                    }
                })
        )
    }

}