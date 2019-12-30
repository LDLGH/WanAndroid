package com.ldl.wanandroid.core.bean

/**
 * 作者：LDL 创建时间：2019/12/27
 * 类说明：
 */
data class BaseResponse<T>(var errorCode: Int, var errorMsg: String, var data: T) {
    companion object {
        const val SUCCESS = 0
        const val FAIL = 1
    }
}