package com.ldl.wanandroid.core.bean.main.login

/**
 * 作者：LDL 创建时间：2020/1/9
 * 类说明：
 */
data class LoginData(
    var admin: Boolean,
    var chapterTops: List<Int>,
    var email: String,
    var id: Long,
    var publicName: String,
    var token: String,
    var collectIds: List<Int>,
    var username: String,
    var password: String,
    var icon: String,
    var type: Int,
    var nickname: String
)