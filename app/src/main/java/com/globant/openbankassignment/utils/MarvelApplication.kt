package com.globant.openbankassignment.utils

import com.globant.openbankassignment.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MarvelApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        InternetUtil.init(this)

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}