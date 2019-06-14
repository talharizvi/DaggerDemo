package com.neosoft.daggerdemosample

import android.app.Application

class MyApplication:Application() {

    lateinit var mApiComponent:ApiComponent

    override fun onCreate() {
        super.onCreate()
        mApiComponent = DaggerApiComponent.builder().
             appModule(AppModule(this))
            .apiModule(ApiModule("https://simplifiedcoding.net/demos/"))
            .build()
    }

    fun getNetComponent() :ApiComponent{
        return mApiComponent
    }
}