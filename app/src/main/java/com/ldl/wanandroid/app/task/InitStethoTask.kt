package com.ldl.wanandroid.app.task

import android.app.Application
import com.facebook.stetho.Stetho
import com.ldl.wanandroid.BuildConfig
import org.jay.launchstarter.MainTask

/**
 * 作者：LDL 创建时间：2020/1/19
 * 类说明：
 */
class InitStethoTask : MainTask() {
    override fun run() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(mContext as Application)
        }
    }
}