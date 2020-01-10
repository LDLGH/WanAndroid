package com.ldl.wanandroid.core.prefs

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
interface PreferenceHelper {

    fun setPassword(password: String)

    fun getPassword(): String

    fun setAccount(account: String)

    fun getAccount(): String

    fun setLoginStatus(loginStatus: Boolean)

    fun getLoginStatus(): Boolean

}