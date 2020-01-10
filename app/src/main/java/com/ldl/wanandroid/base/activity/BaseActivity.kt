package com.ldl.wanandroid.base.activity

import android.os.Bundle
import com.blankj.utilcode.util.SnackbarUtils
import com.blankj.utilcode.util.ToastUtils
import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


/**
 * 作者：LDL 创建时间：2019/12/25
 * 类说明：
 */
abstract class BaseActivity<T : AbstractPresenter> : AbstractSimpleActivity(),
    HasAndroidInjector,
    AbstractView {

    @set:Inject
    lateinit var mAndroidInjector: DispatchingAndroidInjector<Any>
    @set:Inject
    protected var mPresenter: T? = null

    private var loadView: BasePopupView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated() {
        mPresenter?.attachView(this)
    }

    override fun onDestroy() {
        mPresenter?.detachView()
        mPresenter = null
        super.onDestroy()
    }

    override fun androidInjector(): AndroidInjector<Any> = mAndroidInjector

    override fun useNightMode(isNightMode: Boolean) {
    }

    override fun showErrorMsg(errorMsg: String?) {
        SnackbarUtils.with(window.decorView)
            .setMessage(errorMsg!!)
            .showError()
    }

    override fun showNormal() {}

    override fun showError() {}

    override fun showLoading() {}

    override fun reload() {}

    override fun loading() {
        loadView = XPopup.Builder(this)
            .asLoading()
            .show()
    }

    override fun hideLoading() {
        loadView?.dismiss()
        loadView = null
    }

    override fun showCollectSuccess() {
    }

    override fun showCancelCollectSuccess() {
    }

    override fun showToast(message: String?) {
        ToastUtils.showShort(message)
    }

    override fun showSnackBar(message: String?) {
        SnackbarUtils.with(window.decorView)
            .setMessage(message!!)
            .showSuccess()
    }

}