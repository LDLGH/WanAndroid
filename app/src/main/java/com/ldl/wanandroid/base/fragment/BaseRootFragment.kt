package com.ldl.wanandroid.base.fragment

import com.billy.android.loading.Gloading
import com.ldl.wanandroid.base.presenter.BasePresenter

/**
 * 作者：LDL 创建时间：2019/12/25
 * 类说明：
 */
abstract class BaseRootFragment<T : BasePresenter<*>> : BaseFragment<T>() {

    private var mHolder: Gloading.Holder? = null

    private fun initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            mHolder = Gloading.getDefault().wrap(mView).withRetry {
                reload()
            }
        }
    }

    override fun showNormal() {
        initLoadingStatusViewIfNeed()
        mHolder?.showLoadSuccess()
    }

    override fun showError() {
        initLoadingStatusViewIfNeed()
        mHolder?.showLoadFailed()
    }

    override fun showLoading() {
        initLoadingStatusViewIfNeed()
        mHolder?.showLoading()
    }
}