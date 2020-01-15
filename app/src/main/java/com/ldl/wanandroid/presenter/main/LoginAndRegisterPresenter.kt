package com.ldl.wanandroid.presenter.main

import com.blankj.utilcode.util.StringUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.main.LoginAndRegisterContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.event.EventMsg
import com.ldl.wanandroid.core.bean.main.login.LoginData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxBusManager
import com.ldl.wanandroid.utils.RxUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2020/1/9
 * 类说明：
 */
class LoginAndRegisterPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<LoginAndRegisterContract.View>(dataManager), LoginAndRegisterContract.Presenter {

    override fun login(username: String?, password: String?) {
        addSubscribe(
            dataManager.getLoginData(username, password)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .doOnSubscribe {
                    mView?.loading()
                }
                .doFinally {
                    mView?.hideLoading()
                }
                .subscribeWith(object :
                    BaseObserver<LoginData>(mView!!, StringUtils.getString(R.string.login_fail)) {
                    override fun onNext(t: LoginData) {
                        setLoginAccount(t.username)
                        setLoginPassword(t.password)
                        setLoginStatus(true)
                        RxBusManager.post(EventMsg(EventMsg.LOGIN, ""))
                        mView?.onLogin()
                    }
                })
        )
    }

    override fun register(username: String?, password: String?) {
        addSubscribe(
            dataManager.getRegisterData(username, password, password)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .doOnSubscribe {
                    mView?.loading()
                }
                .doFinally {
                    mView?.hideLoading()
                }
                .subscribeWith(object :
                    BaseObserver<LoginData>(mView!!) {
                    override fun onNext(t: LoginData) {
                        login(t.username, t.password)
                    }
                })
        )
    }

    override fun registerAndLogin(username: String?, password: String?) {
        addSubscribe(
            dataManager.getRegisterData(username, password, password)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .observeOn(Schedulers.io())
                .flatMap { dataManager.getLoginData(username, password) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mView?.loading()
                }
                .doFinally {
                    mView?.hideLoading()
                }
                .compose(RxUtils.handleResult())
                .subscribeWith(object :
                    BaseObserver<LoginData>(mView!!) {
                    override fun onNext(t: LoginData) {
                        setLoginAccount(t.username)
                        setLoginPassword(t.password)
                        setLoginStatus(true)
                        RxBusManager.post(EventMsg(EventMsg.LOGIN, ""))
                        mView?.onLogin()
                    }
                })
        )
    }
}