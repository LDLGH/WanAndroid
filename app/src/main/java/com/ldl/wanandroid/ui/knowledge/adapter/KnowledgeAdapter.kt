package com.ldl.wanandroid.ui.knowledge.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.knowledge.KnowledgeHierarchyData

/**
 * 作者：LDL 创建时间：2020/1/16
 * 类说明：
 */
class KnowledgeAdapter(list: ArrayList<KnowledgeHierarchyData>) :
    BaseQuickAdapter<KnowledgeHierarchyData, BaseViewHolder>(
        R.layout.item_staggered_knowledge, list
    ) {
    override fun convert(helper: BaseViewHolder, item: KnowledgeHierarchyData) {
        item.apply {
            helper.setText(R.id.tv_title, name)
            val sb = StringBuilder()
            children.forEach {
                sb.append("${it.name}  ")
            }
            helper.setText(R.id.tv_content, sb)
        }
    }
}