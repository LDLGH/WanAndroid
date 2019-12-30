package com.ldl.wanandroid.base.activity

import com.ldl.wanandroid.base.presenter.BasePresenter

/**
 * 作者：LDL 创建时间：2019/12/25
 * 类说明：
 */
abstract class BaseRootActivity<T : BasePresenter<*>> : BaseActivity<T>() {
}