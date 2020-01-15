package com.ldl.wanandroid.presenter.main

import com.blankj.rxbus.RxBus
import com.blankj.utilcode.util.Utils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.contract.main.HomepageContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.BaseResponse
import com.ldl.wanandroid.core.bean.event.EventMsg
import com.ldl.wanandroid.core.bean.main.ZipMainData
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.menu.MenuData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import com.ldl.wanandroid.core.bean.main.search.UsefulSiteData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxBusManager
import com.ldl.wanandroid.utils.RxUtils
import io.reactivex.Observable
import io.reactivex.functions.Function4
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

    override fun getMenuList(): ArrayList<MenuData> {
        val menuList: ArrayList<MenuData> by lazy { ArrayList<MenuData>() }
        menuList.add(MenuData("文章", ""))
        menuList.add(MenuData("导航", ""))
        menuList.add(MenuData("知识体系", ""))
        menuList.add(MenuData("公众号", ""))
        menuList.add(MenuData("项目", ""))
        return menuList
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
        val observable3 = dataManager.getUsefulSites()
        addSubscribe(
            Observable.zip(observable, observable1, observable2, observable3,
                Function4<BaseResponse<List<TopSearchData>>, BaseResponse<List<BannerData>>, BaseResponse<FeedArticleListData>, BaseResponse<List<UsefulSiteData>>, ZipMainData> { t1, t2, t3, t4 ->
                    ZipMainData(t1, t2, t3, t4)
                }).compose(RxUtils.rxSchedulerHelper())
                .doOnSubscribe {
                    if (isShowLoading) {
                        mView?.showLoading()
                    }
                }
                .subscribeWith(object : BaseObserver<ZipMainData>(mView!!, "", true) {
                    override fun onNext(t: ZipMainData) {
                        if (isShowLoading) {
                            mView?.showNormal()
                        }
                        mView?.apply {
                            showBanner(t.bannerResponse.data)
                            showArticleList(t.feedArticleListResponse.data, isRefresh)
                            showTopTopSearch(t.topSearchBaseResponse.data)
                            showHotSearch(t.usefulSiteDataResponse.data)
                        }
                    }
                })
        )
    }

    override fun attachView(view: AbstractView) {
        super.attachView(view)
        RxBusManager.subscribe(this, object : RxBus.Callback<EventMsg>() {
            override fun onEvent(t: EventMsg?) {
                if (t?.code == EventMsg.LOGIN) {
                    mView?.onLoginEvent()
                }
            }
        })
    }

    override fun detachView() {
        super.detachView()
        RxBusManager.unregister(this)
    }
}