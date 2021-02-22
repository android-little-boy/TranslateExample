package com.example.retrofitcoroutinetranslate

import com.example.retrofitcoroutinetranslate.network.ApiResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TranslateApiService {
    @FormUrlEncoded
    @POST("translate?doctype=json")
    suspend fun translate(@Field("i") i: String): ApiResult<Result>

}

data class Result(
    val type: String,
    val elapsedTime: Int,
    val translateResult: List<List<TranslateResult>>
) {
    data class TranslateResult(
        val src: String,
        val tgt: String
    )
}

/**
 * 实现TranslateApiService的单例化操作
 */
object TranslateApi {
    val retrofitService: TranslateApiService by lazy { retrofit.create(TranslateApiService::class.java) }
}

