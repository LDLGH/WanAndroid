package com.ldl.wanandroid.ui.navigation.fragment

import com.ldl.wanandroid.R.layout.fragment_navigation
import com.ldl.wanandroid.base.fragment.LazyLoadFragment
import com.ldl.wanandroid.contract.navigation.NavigationContract
import com.ldl.wanandroid.presenter.navigation.NavigationPresenter

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class NavigationFragment : LazyLoadFragment<NavigationPresenter>(), NavigationContract.View {

    override fun loadData() {
    }

    override fun getLayoutId(): Int = fragment_navigation

    override fun initEventAndData() {
    }
}