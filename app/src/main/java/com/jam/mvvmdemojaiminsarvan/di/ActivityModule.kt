package com.jam.mvvmdemojaiminsarvan.di

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jam.mvvmdemojaiminsarvan.data.repository.TopHeadlinePageRepository
import com.jam.mvvmdemojaiminsarvan.data.repository.TopHeadlineRepository
import com.jam.mvvmdemojaiminsarvan.data.repository.TopHeadlineSearchRepository
import com.jam.mvvmdemojaiminsarvan.ui.base.ViewModelProviderFactory
import com.jam.mvvmdemojaiminsarvan.ui.topheadline.TopHeadlineAdapter
import com.jam.mvvmdemojaiminsarvan.ui.topheadline.TopHeadlinePagerViewModel
import com.jam.mvvmdemojaiminsarvan.ui.topheadline.TopHeadlineSearchViewModel
import com.jam.mvvmdemojaiminsarvan.ui.topheadline.TopHeadlineViewModel
import dagger.Module
import dagger.Provides
import java.util.ArrayList

@Module
class ActivityModule(private val activity: ComponentActivity)
{
    @ActivityContext
    @Provides
    fun provideActivityContext(): Context{
      return activity
    }

    @Provides
    fun provideTopHeadlineViewModel(topHeadlineRepository: TopHeadlineRepository) : TopHeadlineViewModel {
        return ViewModelProvider(activity,ViewModelProviderFactory(TopHeadlineViewModel::class){
              TopHeadlineViewModel(topHeadlineRepository)
        }) [TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlinePagerViewModel(topHeadlinePageRepository: TopHeadlinePageRepository) : TopHeadlinePagerViewModel {
        return ViewModelProvider(activity,ViewModelProviderFactory(TopHeadlinePagerViewModel::class){
            TopHeadlinePagerViewModel(topHeadlinePageRepository)
        }) [TopHeadlinePagerViewModel::class.java]
    }

    @Provides
    fun provideTopHEadlineSearchViewModel(searchRepository: TopHeadlineSearchRepository): TopHeadlineSearchViewModel {
        return ViewModelProvider(activity,ViewModelProviderFactory(TopHeadlineSearchViewModel::class){
            TopHeadlineSearchViewModel(searchRepository)
        })[TopHeadlineSearchViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())


}