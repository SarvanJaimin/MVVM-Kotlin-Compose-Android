package com.jam.mvvmdemojaiminsarvan

import android.app.Application
import com.jam.mvvmdemojaiminsarvan.di.ApplicationModule
import com.jam.mvvmdemojaiminsarvan.di.component.ApplicationComponent
import com.jam.mvvmdemojaiminsarvan.di.component.DaggerApplicationComponent


class MVVMApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies(){
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(
            ApplicationModule(this)
        ).build()
        applicationComponent.inject(this)
    }
}