package com.ldl.wanandroid.contract.project

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.bean.project.ProjectClassifyData

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
interface ProjectContract {

    interface View : AbstractView {
        fun showProjectClassify(projectClassifyDataList: List<ProjectClassifyData>)
    }

    interface Presenter : AbstractPresenter {
        fun getProjectClassifyData()
    }
}