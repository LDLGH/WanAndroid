package com.ldl.wanandroid.contract.knowledge

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView

/**
 * 作者：LDL 创建时间：2020/1/16
 * 类说明：
 */
interface KnowledgeDetailContract {

    interface View : AbstractView {
        fun getBundleData()
    }

    interface Presenter : AbstractPresenter
}