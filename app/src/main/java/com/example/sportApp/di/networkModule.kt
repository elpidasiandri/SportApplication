package com.example.sportApp.di

import com.example.sportApp.BuildConfig
import com.example.sportApp.network.SportClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single<SportClient> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

        retrofit.create(SportClient::class.java)
    }
}