package com.jam.mvvmdemojaiminsarvan.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jam.mvvmdemojaiminsarvan.data.models.Article
import com.jam.mvvmdemojaiminsarvan.data.repository.TopHeadlineRepository
import com.jam.mvvmdemojaiminsarvan.ui.base.UiState
import com.jam.mvvmdemojaiminsarvan.utils.AppConstant.COUNTRY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TopHeadlineViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

     init {
         fetchTopHeadlines()
     }

    private fun fetchTopHeadlines() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(COUNTRY)
                .catch { e ->
                     _uiState.value = UiState.Error(e.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}