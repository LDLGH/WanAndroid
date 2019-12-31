package com.ldl.wanandroid.contract.main

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView

/**
 * 作者：LDL 创建时间：2019/12/30
 * 类说明：
 */
interface SplashContract {


    interface View : AbstractView {
        fun jumpToMain()
    }

    interface Presenter : AbstractPresenter
}