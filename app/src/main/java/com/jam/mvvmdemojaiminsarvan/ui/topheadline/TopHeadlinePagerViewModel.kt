package com.jam.mvvmdemojaiminsarvan.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jam.mvvmdemojaiminsarvan.data.models.Article
import com.jam.mvvmdemojaiminsarvan.data.repository.TopHeadlinePageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopHeadlinePagerViewModel(private val repository: TopHeadlinePageRepository) : ViewModel(){

    private val _topHeadlines = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val topHeadlines: StateFlow<PagingData<Article>> = _topHeadlines.asStateFlow()

    init {
        getTopHeadlines()
    }


    fun getTopHeadlines() {
        viewModelScope.launch {
            repository.getTopHealinePage().cachedIn(viewModelScope).collect{
                    pagingData -> _topHeadlines.value = pagingData
            }
        }
    }

    fun deleteTopHeadlines(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTopHealinePage()
        }
    }
}