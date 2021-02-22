package com.example.retrofitcoroutinetranslate

import com.example.retrofitcoroutinetranslate.network.errorIntereptor.BusinessErrorInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://fanyi.youdao.com/"

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(BusinessErrorInterceptor()) //添加判断errorCode是否为0的拦截器
    .build()


val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(ApiResultCallAdapterFactory())  //添加自定义的call<T> 对象转换工厂
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttpClient) // 添加客户端
    .build()