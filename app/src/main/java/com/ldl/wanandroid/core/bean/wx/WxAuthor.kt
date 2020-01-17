package com.ldl.wanandroid.core.bean.wx

/**
 * 作者：LDL 创建时间：2020/1/17
 * 类说明：
 */
data class WxAuthor(
    var courseId: Int,
    var id: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var userControlSetTop: Boolean,
    var visible: Int
)