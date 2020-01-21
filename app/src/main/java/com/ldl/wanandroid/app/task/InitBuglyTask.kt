package com.ldl.wanandroid.app.task

import android.app.Application
import com.ldl.wanandroid.BuildConfig
import com.ldl.wanandroid.app.Constants
import com.tencent.bugly.crashreport.CrashReport
import org.jay.launchstarter.MainTask

/**
 * 作者：LDL 创建时间：2020/1/19
 * 类说明：
 */
class InitBuglyTask : MainTask() {

    override fun run() {
        CrashReport.initCrashReport(mContext as Application, Constants.BUGLY_ID, BuildConfig.DEBUG)
    }
}