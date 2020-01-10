package com.ldl.wanandroid.base.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.blankj.utilcode.util.SnackbarUtils
import com.blankj.utilcode.util.ToastUtils
import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/25
 * 类说明：
 */
abstract class BaseDialogFragment<T : AbstractPresenter> : AbstractSimpleDialogFragment(),
    AbstractView {

    protected lateinit var mActivity: Activity

    @set:Inject
    protected var mPresenter: T? = null

    private var loadView: BasePopupView? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter?.attachView(this)
    }

    override fun onDestroyView() {
        mPresenter?.detachView()
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
        mPresenter = null
    }

    override fun useNightMode(isNightMode: Boolean) {
    }

    override fun showErrorMsg(errorMsg: String?) {
        SnackbarUtils.with(mActivity.window.decorView)
            .setMessage(errorMsg!!)
            .showError()
    }

    override fun showNormal() {}

    override fun showError() {}

    override fun showLoading() {}

    override fun reload() {}

    override fun showCollectSuccess() {
    }

    override fun showCancelCollectSuccess() {
    }

    override fun loading() {
        loadView = XPopup.Builder(mActivity)
            .asLoading()
            .show()
    }

    override fun hideLoading() {
        loadView?.dismiss()
        loadView = null
    }

    override fun showToast(message: String?) {
        ToastUtils.showShort(message)
    }

    override fun showSnackBar(message: String?) {
        SnackbarUtils.with(mActivity.window.decorView)
            .setMessage(message!!)
            .showSuccess()
    }
}