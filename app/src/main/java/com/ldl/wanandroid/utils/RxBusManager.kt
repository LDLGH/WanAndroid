package com.ldl.wanandroid.utils

import com.blankj.rxbus.RxBus
import com.ldl.wanandroid.core.bean.event.EventMsg

/**
 * 作者：LDL 创建时间：2019/12/16
 * 类说明：
 */
object RxBusManager {

    private const val TAG = "WanAndroidApp"

    fun <T> subscribe(
        subscriber: Any,
        callback: RxBus.Callback<T>
    ) {
        RxBus.getDefault().subscribe(subscriber, callback)
    }

    fun <T> subscribeTag(
        subscriber: Any,
        callback: RxBus.Callback<T>
    ) {
        RxBus.getDefault().subscribe(subscriber, TAG, callback)
    }

    fun post(msg: EventMsg) {
        RxBus.getDefault().post(msg)
    }

    fun postWithMyTag(msg: EventMsg) {
        RxBus.getDefault().post(msg, TAG)
    }

    fun postSticky(msg: EventMsg) {
        RxBus.getDefault().postSticky(msg)
    }

    fun postStickyWithMyTag(msg: EventMsg) {
        RxBus.getDefault().postSticky(msg, TAG)
    }

    fun unregister(subscriber: Any) {
        RxBus.getDefault().unregister(subscriber)
    }

}