package com.ldl.wanandroid.contract.knowledge

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.bean.knowledge.KnowledgeHierarchyData

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
interface KnowledgeContract {

    interface View : AbstractView {
        fun showKnowledgeHierarchy(knowledgeHierarchyDataList: List<KnowledgeHierarchyData>)
    }

    interface Presenter : AbstractPresenter {
        fun getKnowledgeHierarchyData()
    }
}