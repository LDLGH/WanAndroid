package com.ldl.wanandroid.contract.main

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView

/**
 * 作者：LDL 创建时间：2019/12/27
 * 类说明：
 */
interface MainContract {

    interface View : AbstractView {
        fun onLogout()

        fun onLoginEvent(msg: String)
    }

    interface Presenter : AbstractPresenter {
        fun logout()
    }
}