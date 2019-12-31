package com.ldl.wanandroid.base.fragment

import com.ldl.wanandroid.base.presenter.BasePresenter

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
abstract class LazyLoadFragment<T : BasePresenter<*>> : BaseRootFragment<T>() {

    /**
     * 数据是否已请求
     */
    private var isDataLoaded = false

    /**
     * 实现具体的数据请求逻辑
     */
    protected abstract fun loadData()

    /**
     * fragment再次可见时，是否重新请求数据，默认为false则只请求一次数据
     */
    protected open fun isNeedReload(): Boolean {
        return false
    }

    /**
     * 使用show()、hide()控制fragment显示、隐藏时回调该方法
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            tryLoadData()
        }
    }

    /**
     * show()、hide()场景下，尝试请求数据
     */
    open fun tryLoadData() {
        if (isNeedReload() || !isDataLoaded) {
            loadData()
            isDataLoaded = true
        }
    }
}