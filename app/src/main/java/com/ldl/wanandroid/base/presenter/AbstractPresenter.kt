package com.ldl.wanandroid.base.presenter

import com.ldl.wanandroid.base.view.AbstractView
import io.reactivex.disposables.Disposable

/**
 * 作者：LDL 创建时间：2019/12/25
 * 类说明：Presenter 基类
 */
interface AbstractPresenter {

    /**
     * 注入View
     *
     * @param view view
     */
    fun attachView(view: AbstractView)

    /**
     * 回收View
     */
    fun detachView()

    /**
     * Add rxBing subscribe manager
     *
     * @param disposable Disposable
     */
    fun addRxBindingSubscribe(disposable: Disposable?)

    /**
     * Get night mode state
     *
     * @return if is night mode
     */
    fun getNightModeState(): Boolean

    /**
     * Set login status
     *
     * @param loginStatus login status
     */
    fun setLoginStatus(loginStatus: Boolean)

    /**
     * Get login status
     *
     * @return if is login status
     */
    fun getLoginStatus(): Boolean

    /**
     * Get login account
     *
     * @return login account
     */
    fun getLoginAccount(): String?

    /**
     * Set login status
     *
     * @param account account
     */
    fun setLoginAccount(account: String?)

    /**
     * Set login password
     *
     * @param password password
     */
    fun setLoginPassword(password: String?)

    /**
     * Get current page
     *
     * @return current page
     */
    fun getCurrentPage(): Int
}