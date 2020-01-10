package com.ldl.wanandroid.core.prefs

import com.blankj.utilcode.util.SPStaticUtils
import com.ldl.wanandroid.app.Constants
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
class PreferenceHelperImpl @Inject constructor() : PreferenceHelper {

    override fun setPassword(password: String) = SPStaticUtils.put(Constants.PASSWORD, password)

    override fun getPassword(): String = SPStaticUtils.getString(Constants.PASSWORD)

    override fun setAccount(account: String) = SPStaticUtils.put(Constants.ACCOUNT, account)

    override fun getAccount(): String = SPStaticUtils.getString(Constants.ACCOUNT)

    override fun setLoginStatus(loginStatus: Boolean) =
        SPStaticUtils.put(Constants.LOGIN_STATUS, loginStatus)

    override fun getLoginStatus(): Boolean = SPStaticUtils.getBoolean(Constants.LOGIN_STATUS)
}