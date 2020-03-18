package com.ldl.wanandroid.presenter.main

import com.blankj.rxbus.RxBus
import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.contract.main.MainContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.event.EventMsg
import com.ldl.wanandroid.core.bean.main.login.LoginData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxBusManager
import com.ldl.wanandroid.utils.RxUtils
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/27
 * 类说明：
 */
class MainPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<MainContract.View>(dataManager), MainContract.Presenter {

    override fun logout() {
        addSubscribe(dataManager.logout()
            .compose(RxUtils.rxSchedulerHelper())
            .compose(RxUtils.handleLogoutResult())
            .doOnSubscribe {
                mView?.loading()
            }
            .doFinally {
                mView?.hideLoading()
            }
            .subscribeWith(object :
                BaseObserver<LoginData>(mView!!) {
                override fun onNext(t: LoginData) {
                    setLoginAccount("")
                    setLoginPassword("")
                    setLoginStatus(false)
                    RxBusManager.post(EventMsg(EventMsg.LOGIN, "logout"))
                    mView?.onLogout()
                }
            })
        )
    }

    override fun attachView(view: AbstractView) {
        super.attachView(view)
        RxBusManager.subscribe(this, object : RxBus.Callback<EventMsg>() {
            override fun onEvent(t: EventMsg?) {
                if (t?.code == EventMsg.LOGIN) {
                    mView?.onLoginEvent(t.msg)
                }
            }
        })
    }

    override fun detachView() {
        super.detachView()
        RxBusManager.unregister(this)
    }

}