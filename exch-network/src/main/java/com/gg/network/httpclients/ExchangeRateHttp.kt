package com.gg.network.httpclients

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ExchangeRateHttp {
    companion object {

        private const val HEADER_API_KEY = "apikey"

        private fun getOkHttpClient(token: String): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().also {
                    it.setLevel(HttpLoggingInterceptor.Level.BODY)
                })
                .addInterceptor(Interceptor { chain ->
                    val original: Request = chain.request()

                    val request: Request = original.newBuilder()
                        .header(HEADER_API_KEY, token)
                        .build()
                    chain.proceed(request)
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
        }

        fun getRetrofit(token: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.apilayer.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient(token = token))
                .build()
        }

    }
}