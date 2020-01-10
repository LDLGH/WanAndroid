package com.ldl.wanandroid.base.view

/**
 * 作者：LDL 创建时间：2019/12/25
 * 类说明：View 基类
 */
interface AbstractView {

    /**
     * Use night mode
     *
     * @param isNightMode if is night mode
     */
    fun useNightMode(isNightMode: Boolean)

    /**
     * Show error message
     *
     * @param errorMsg error message
     */
    fun showErrorMsg(errorMsg: String?)

    /**
     * showNormal
     */
    fun showNormal()

    /**
     * Show error
     */
    fun showError()

    /**
     * Show loading
     */
    fun showLoading()

    /**
     * Reload
     */
    fun reload()

    fun loading()

    fun hideLoading()

    /**
     * Show collect success
     */
    fun showCollectSuccess()

    /**
     * Show cancel collect success
     */
    fun showCancelCollectSuccess()

    /**
     * Show toast
     *
     * @param message Message
     */
    fun showToast(message: String?)

    /**
     * Show snackBar
     *
     * @param message Message
     */
    fun showSnackBar(message: String?)

}