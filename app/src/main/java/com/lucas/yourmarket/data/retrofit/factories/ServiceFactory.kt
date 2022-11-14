package com.lucas.yourmarket.data.retrofit.factories

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.component.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class ServiceFactory<T> : KoinComponent {

    companion object {
        private const val CONTENT_TYPE = "Content-Type"
        private const val APPLICATION_JSON = "application/json"
    }

    protected open val baseUrl: String by lazy { com.lucas.yourmarket.BuildConfig.MELI_BASE_URL }

    abstract fun produce(): T

    protected val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }).addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                    .build()
                chain.proceed(request)
            }.build()
    }
}
