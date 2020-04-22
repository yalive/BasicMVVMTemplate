package com.yabahddou.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yabahddou.data.net.RestApi
import com.yabahddou.common.event.Constants
import com.yabahddou.data.net.customFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: Lazy<OkHttpClient>, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .customFactory { client.get().newCall(it) } // Initialize Okhttp lazily
            .build()
    }

    @Singleton
    @Provides
    fun providesOkHttp(context: Context): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .connectTimeout((3 * 60).toLong(), TimeUnit.SECONDS)
            .writeTimeout((3 * 60).toLong(), TimeUnit.SECONDS)
            .readTimeout((3 * 60).toLong(), TimeUnit.SECONDS)
            .addInterceptor(logging)

        return builder.build()
    }

    @Singleton
    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideRestApiService(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }
}