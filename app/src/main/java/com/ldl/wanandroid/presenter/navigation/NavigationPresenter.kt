package com.ldl.wanandroid.presenter.navigation

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.navigation.NavigationContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.navigation.NavigationListData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxUtils
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class NavigationPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<NavigationContract.View>(dataManager), NavigationContract.Presenter {

    override fun getNavigationListData() {
        addSubscribe(
            dataManager.getNavigationListData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(object : BaseObserver<ArrayList<NavigationListData>>(mView!!) {
                    override fun onNext(t: ArrayList<NavigationListData>) {
                        mView?.showNavigationList(t)
                    }
                })
        )
    }


}