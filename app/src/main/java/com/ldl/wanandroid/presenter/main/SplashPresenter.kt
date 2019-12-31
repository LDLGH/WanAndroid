package com.ldl.wanandroid.presenter.main

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.contract.main.SplashContract
import com.ldl.wanandroid.core.DataManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/30
 * 类说明：
 */
class SplashPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<SplashContract.View>(dataManager), SplashContract.Presenter {

    override fun attachView(view: AbstractView) {
        super.attachView(view)
        addSubscribe(Observable.timer(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mView?.jumpToMain()
            }
        )
    }
}