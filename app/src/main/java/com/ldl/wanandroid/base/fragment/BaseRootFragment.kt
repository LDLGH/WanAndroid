package com.ldl.wanandroid.base.fragment

import com.ldl.wanandroid.base.presenter.BasePresenter

/**
 * 作者：LDL 创建时间：2019/12/25
 * 类说明：
 */
abstract class BaseRootFragment<T : BasePresenter<*>> : BaseFragment<T>() {


}