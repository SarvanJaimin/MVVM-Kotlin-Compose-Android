package com.jam.mvvmdemojaiminsarvan.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.jam.mvvmdemojaiminsarvan.data.models.Article
import com.jam.mvvmdemojaiminsarvan.data.models.TopHeadlinesResponse
import com.jam.mvvmdemojaiminsarvan.data.repository.TopHeadlineSearchRepository
import com.jam.mvvmdemojaiminsarvan.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class TopHeadlineSearchViewModel(private val topHeadlineSearchRepository: TopHeadlineSearchRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    private val _searchQuery = MutableStateFlow("")

    init {
        observeSearchQuery()
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

     fun observeSearchQuery(){
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .filter {
                    if(it.isEmpty()){
                        _uiState.value = UiState.Success(emptyList())
                        return@filter false
                    } else {
                        return@filter true
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest { it ->
                    topHeadlineSearchRepository.getTopHeadlineSearch(it)
                        .catch {
                            emit(TopHeadlinesResponse())
                            _uiState.value = UiState.Error(it.toString())
                        }
                }
                .collect{
                    _uiState.value = UiState.Success(it.articles)
                }
        }
    }
}