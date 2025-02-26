package com.jam.mvvmdemojaiminsarvan.di.component

import com.jam.mvvmdemojaiminsarvan.MainActivity
import com.jam.mvvmdemojaiminsarvan.SearchTopHeadLineActivity
import com.jam.mvvmdemojaiminsarvan.TopHeadLineActivity
import com.jam.mvvmdemojaiminsarvan.di.ActivityModule
import com.jam.mvvmdemojaiminsarvan.di.ActivityScope
import dagger.Component


@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
    fun inject(topHeadLineActivity: TopHeadLineActivity)
    fun inject(searchTopHeadLineActivity: SearchTopHeadLineActivity)
}