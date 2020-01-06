package com.ldl.wanandroid.core.bean.main.collect

/**
 * 作者：LDL 创建时间：2019/12/27
 * 类说明：
 */
data class FeedArticleData(
    var apkLink: String,
    var author: String = "Android",
    var chapterId: Int,
    var chapterName: String,
    var collect: Boolean,
    var courseId: Int,
    var desc: String,
    var envelopePic: String,
    var id: Int,
    var link: String,
    var niceDate: String,
    var origin: String,
    var projectLink: String,
    var superChapterId: Int,
    var superChapterName: String,
    var publishTime: Long,
    var title: String = "",
    var visible: Int,
    var zan: Int
)