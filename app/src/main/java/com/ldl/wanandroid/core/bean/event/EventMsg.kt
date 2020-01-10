package com.ldl.wanandroid.core.bean.event

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 作者：LDL 创建时间：2019/12/16
 * 类说明：
 */
@Parcelize
data class EventMsg(var code: Int, var msg: String) : Parcelable {

    companion object {
        const val LOGIN = 0x0001
    }
}