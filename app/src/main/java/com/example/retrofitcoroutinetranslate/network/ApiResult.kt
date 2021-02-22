package com.example.retrofitcoroutinetranslate.network

/**
 * kotlin特有的密封类，主要用于把 @POST 返回的call对象经过自定义的ApiResultCallAdapter转换成ApiResult，便于处理异常情况
 */
sealed class ApiResult<out T> {
    data class Success<out T>(val data: T?) : ApiResult<T>()
    data class Failure(val errorCode: Int, val errorMsg: String) : ApiResult<Nothing>()
}

