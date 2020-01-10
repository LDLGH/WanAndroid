package com.ldl.wanandroid.core.http.rx

import com.blankj.utilcode.util.ObjectUtils
import com.blankj.utilcode.util.Utils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.http.exception.OtherException
import com.ldl.wanandroid.core.http.exception.ServerException
import io.reactivex.observers.ResourceObserver
import retrofit2.HttpException

/**
 * 作者：LDL 创建时间：2019/12/27
 * 类说明：
 */
abstract class BaseObserver<T> constructor(
    var view: AbstractView,
    var errorMsg: String = "",
    var isShowError: Boolean = false
) : ResourceObserver<T>() {


    override fun onComplete() {

    }

    override fun onError(e: Throwable) {
        when {
            ObjectUtils.isNotEmpty(errorMsg) -> {
                view.showErrorMsg(errorMsg)
            }
            e is ServerException -> {
                view.showErrorMsg(e.toString())
            }
            e is HttpException -> {
                view.showErrorMsg(Utils.getApp().getString(R.string.http_error))
            }
            else -> {
                view.showErrorMsg(Utils.getApp().getString(R.string.unKnown_error))
            }
        }
        if (isShowError) {
            view.showError()
        }
    }
}