package com.ldl.wanandroid.app.task

import android.app.Application
import com.blankj.utilcode.util.Utils
import org.jay.launchstarter.MainTask

/**
 * 作者：LDL 创建时间：2020/1/19
 * 类说明：
 */
class InitUtilsTask : MainTask() {
    override fun run() {
        Utils.init(mContext as Application)
    }
}