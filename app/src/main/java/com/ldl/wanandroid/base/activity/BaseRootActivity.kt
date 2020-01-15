package com.ldl.wanandroid.base.activity

import android.view.View
import com.billy.android.loading.Gloading
import com.ldl.wanandroid.base.presenter.BasePresenter


/**
 * 作者：LDL 创建时间：2019/12/25
 * 类说明：
 */
abstract class BaseRootActivity<T : BasePresenter<*>> : BaseActivity<T>() {

    private var mHolder: Gloading.Holder? = null

    protected fun initLoadingStatusViewIfNeed(view : View) {
        if (mHolder == null) {
            mHolder = Gloading.getDefault().wrap(view).withRetry {
                reload()
            }
        }
    }

    override fun showNormal() {
        mHolder?.showLoadSuccess()
    }

    override fun showError() {
        mHolder?.showLoadFailed()
    }

    override fun showLoading() {
        mHolder?.showLoading()
    }

}