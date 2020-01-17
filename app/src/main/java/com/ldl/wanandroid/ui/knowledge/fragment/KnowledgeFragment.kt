package com.ldl.wanandroid.ui.knowledge.fragment

import com.ldl.wanandroid.R.layout.fragment_knowledge
import com.ldl.wanandroid.base.fragment.LazyLoadFragment
import com.ldl.wanandroid.contract.knowledge.KnowledgeContract
import com.ldl.wanandroid.core.bean.knowledge.KnowledgeHierarchyData
import com.ldl.wanandroid.presenter.knowledge.KnowledgePresenter

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class KnowledgeFragment : LazyLoadFragment<KnowledgePresenter>(), KnowledgeContract.View {

    override fun loadData() {

    }

    override fun getLayoutId(): Int = fragment_knowledge

    override fun initEventAndData() {
    }

    override fun showKnowledgeHierarchy(knowledgeHierarchyDataList: List<KnowledgeHierarchyData>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}