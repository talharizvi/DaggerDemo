package com.neosoft.daggerdemosample

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(mApplication:Application) {
    var mApplication:Application

    init {
        this.mApplication = mApplication
    }

    @Provides
    @Singleton
    fun provideApplication():Application{
        return mApplication
    }

}