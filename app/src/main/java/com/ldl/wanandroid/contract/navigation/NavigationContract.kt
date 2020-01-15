package com.ldl.wanandroid.contract.navigation

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.bean.navigation.NavigationListData

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
interface NavigationContract {

    interface View : AbstractView {

        fun showNavigationList(navigationListDataList: ArrayList<NavigationListData>)

    }

    interface Presenter : AbstractPresenter {
        fun getNavigationListData()
    }
}