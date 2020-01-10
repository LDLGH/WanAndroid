package com.ldl.wanandroid.presenter.main

import com.blankj.utilcode.util.ObjectUtils
import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.main.SearchContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import com.ldl.wanandroid.core.dao.HistoryData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxUtils
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2020/1/7
 * 类说明：
 */
class SearchPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<SearchContract.View>(dataManager), SearchContract.Presenter {

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

    override fun addHistoryData(data: String?) {
        if (ObjectUtils.isEmpty(data)) {
            return
        }
        addSubscribe(
            Observable.create(ObservableOnSubscribe<List<HistoryData>> {
                val list = dataManager.addHistoryData(data)
                it.onNext(list)
            })
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe {
                    mView?.showHistory(it)
                }
        )
    }

    override fun clearHistoryData() {
        dataManager.clearHistoryData()
    }

    override fun loadAllHistoryData() {
        mView?.showHistory(dataManager.loadAllHistoryData())
    }

}