package com.neosoft.daggerdemosample

import android.app.Application
import dagger.Module
import okhttp3.Cache
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import javax.inject.Singleton
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient




//dependency provider
@Module
class ApiModule(mBaseUrl:String) {
    val mBaseUrl:String

    init {
        this.mBaseUrl = mBaseUrl
    }

    @Provides
    @Singleton
    fun provideHttpCache(application:Application):Cache{
        val cacheSize:Int = 10 * 1024 * 1024
        val cache:Cache = Cache(application.cacheDir, cacheSize.toLong())
        return cache
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache)
        return client.build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(mBaseUrl)
            .client(okHttpClient)
            .build()
    }



}