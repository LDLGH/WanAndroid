package com.ldl.wanandroid.app

import com.blankj.utilcode.util.Utils
import java.io.File

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
class Constants {

    companion object {

        val PATH_DATA =
            Utils.getApp().cacheDir.absolutePath.toString() + File.separator + "data"

        val PATH_CACHE = "$PATH_DATA/NetCache"

        const val DB_NAME = "wan_android.db"

        const val BUGLY_ID = "68b4f35622"
    }


}