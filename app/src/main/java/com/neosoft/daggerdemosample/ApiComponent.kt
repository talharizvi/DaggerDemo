package com.neosoft.daggerdemosample

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class,AppModule::class])
interface ApiComponent {

    fun inject(activity:MainActivity)
}