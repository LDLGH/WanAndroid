package com.ldl.wanandroid.core.bean.main.menu

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 作者：LDL 创建时间：2020/1/6
 * 类说明：
 */
@Parcelize
data class MenuData(var name: String, var url: String) : Parcelable