package com.ldl.wanandroid.utils

import com.blankj.utilcode.util.ObjectUtils
import com.ldl.wanandroid.core.bean.BaseResponse
import com.ldl.wanandroid.core.bean.main.login.LoginData
import com.ldl.wanandroid.core.http.exception.OtherException
import com.ldl.wanandroid.core.http.exception.ServerException
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * 作者：LDL 创建时间：2019/12/27
 * 类说明：
 */
object RxUtils {

    /**
     * 统一线程处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    fun <T> rxSchedulerHelper(): ObservableTransformer<T, T>? {
        return ObservableTransformer { observable: Observable<T> ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }


    /**
     * 统一返回结果处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
    </T> */
    fun <T> handleResult(): ObservableTransformer<BaseResponse<T>, T>? {
        return ObservableTransformer<BaseResponse<T>, T> { httpResponseObservable: Observable<BaseResponse<T>> ->
            httpResponseObservable.flatMap(Function<BaseResponse<T>, Observable<T>> { baseResponse: BaseResponse<T> ->
                if (baseResponse.errorCode == BaseResponse.SUCCESS
                    && baseResponse.data != null
                ) {
                    return@Function createData(baseResponse.data)
                } else if (ObjectUtils.isNotEmpty(baseResponse.errorMsg)) {
                    return@Function Observable.error(ServerException(baseResponse.errorMsg))
                } else {
                    return@Function Observable.error(OtherException())
                }
            })
        }
    }

    /**
     * 退出登录返回结果处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    fun <T> handleLogoutResult(): ObservableTransformer<BaseResponse<T>, T>? {
        return ObservableTransformer<BaseResponse<T>, T> { httpResponseObservable: Observable<BaseResponse<T>> ->
            httpResponseObservable.flatMap(Function<BaseResponse<T>, Observable<T>> { baseResponse: BaseResponse<T> ->
                if (baseResponse.errorCode == BaseResponse.SUCCESS) {
                    return@Function createData(
                        cast(
                            LoginData(
                                false, arrayListOf(), "", 0, "", "",
                                arrayListOf(), "", "", "", 0, ""
                            )
                        )
                    )
                } else {
                    return@Function Observable.error(OtherException())
                }
            })
        }
    }

    /**
     * 得到 Observable
     * @param <T> 指定的泛型类型
     * @return Observable
     */
    private fun <T> createData(t: T): Observable<T> {
        return Observable.create { emitter: ObservableEmitter<T> ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    /**
     * 泛型转换工具方法 eg:object ==> map<String></String>, String>
     *
     * @param data data
     * @param <T> 转换得到的泛型对象
     * @return T
    </T> */
    private fun <T> cast(data: Any): T {
        @Suppress("UNCHECKED_CAST")
        return data as T
    }

}