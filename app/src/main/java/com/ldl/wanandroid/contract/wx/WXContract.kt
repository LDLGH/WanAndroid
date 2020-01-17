package com.ldl.wanandroid.contract.wx

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.bean.wx.WxAuthor

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
interface WXContract {

    interface View : AbstractView {
        fun showWxAuthorList(wxAuthorList: List<WxAuthor>)
    }

    interface Presenter : AbstractPresenter {
        fun getWxAuthorListData()
    }
}