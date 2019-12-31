package com.ldl.wanandroid.ui.wx.fragment

import com.ldl.wanandroid.R.layout.fragment_wx
import com.ldl.wanandroid.base.fragment.LazyLoadFragment
import com.ldl.wanandroid.contract.wx.WXContract
import com.ldl.wanandroid.presenter.wx.WXPresenter

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class WXFragment : LazyLoadFragment<WXPresenter>(), WXContract.View {

    override fun loadData() {
    }

    override fun getLayoutId(): Int = fragment_wx

    override fun initEventAndData() {
    }
}