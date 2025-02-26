package com.jam.mvvmdemojaiminsarvan.di.component

import android.content.Context
import com.jam.mvvmdemojaiminsarvan.MVVMApplication
import com.jam.mvvmdemojaiminsarvan.data.api.NetworkService
import com.jam.mvvmdemojaiminsarvan.data.repository.TopHeadlinePageRepository
import com.jam.mvvmdemojaiminsarvan.data.repository.TopHeadlineRepository
import com.jam.mvvmdemojaiminsarvan.data.repository.TopHeadlineSearchRepository
import com.jam.mvvmdemojaiminsarvan.di.ApplicationContext
import com.jam.mvvmdemojaiminsarvan.di.ApplicationModule
import com.jam.mvvmdemojaiminsarvan.di.DatabaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class,DatabaseModule::class])
interface ApplicationComponent {

    fun inject(application: MVVMApplication)


    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository

    fun getTopHeadlinePageRepository(): TopHeadlinePageRepository

    fun getTopHeadlineSearchRepository(): TopHeadlineSearchRepository

}