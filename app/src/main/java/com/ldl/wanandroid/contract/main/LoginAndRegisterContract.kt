package com.ldl.wanandroid.contract.main

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.bean.main.login.LoginData

/**
 * 作者：LDL 创建时间：2020/1/9
 * 类说明：
 */
interface LoginAndRegisterContract {

    interface View : AbstractView {

        fun onLogin()

    }

    interface Presenter : AbstractPresenter {

        fun login(username: String?, password: String?)

        fun register(username: String?, password: String?)

        fun registerAndLogin(username: String?, password: String?)
    }
}