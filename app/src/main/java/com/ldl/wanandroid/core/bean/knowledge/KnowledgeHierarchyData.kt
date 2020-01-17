package com.ldl.wanandroid.core.bean.knowledge

/**
 * 作者：LDL 创建时间：2020/1/16
 * 类说明：
 */
data class KnowledgeHierarchyData(
    var children: List<KnowledgeHierarchyData>,
    var courseId: Int,
    var id: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var visible: Int
)