package com.ldl.wanandroid.base.presenter

import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.DataManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 作者：LDL 创建时间：2019/12/25
 * 类说明： Base Presenter
 * 管理事件流订阅的生命周期
 */
open class BasePresenter<T : AbstractView> constructor() : AbstractPresenter {

    protected var mView: T? = null

    protected var compositeDisposable: CompositeDisposable? = null

    private lateinit var dataManager: DataManager

    constructor(dataManager: DataManager) : this() {
        this.dataManager = dataManager
    }

    @Suppress("UNCHECKED_CAST")
    override fun attachView(view: AbstractView) {
        mView = view as T
    }

    override fun detachView() {
        mView = null
        compositeDisposable?.clear()
    }

    override fun addRxBindingSubscribe(disposable: Disposable?) {
        addSubscribe(disposable)
    }

    override fun getNightModeState(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLoginStatus(loginStatus: Boolean) = dataManager.setLoginStatus(loginStatus)

    override fun getLoginStatus(): Boolean = dataManager.getLoginStatus()

    override fun getLoginAccount(): String? = dataManager.getAccount()

    override fun setLoginAccount(account: String?) = dataManager.setAccount(account!!)

    override fun setLoginPassword(password: String?) = dataManager.setPassword(password!!)

    override fun getCurrentPage(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    protected fun addSubscribe(disposable: Disposable?) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable!!)
    }


}