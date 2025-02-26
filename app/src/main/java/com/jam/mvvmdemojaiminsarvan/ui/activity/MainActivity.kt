package com.jam.mvvmdemojaiminsarvan.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jam.mvvmdemojaiminsarvan.MVVMApplication
import com.jam.mvvmdemojaiminsarvan.data.models.Article
import com.jam.mvvmdemojaiminsarvan.databinding.ActivityMainBinding
import com.jam.mvvmdemojaiminsarvan.di.ActivityModule
import com.jam.mvvmdemojaiminsarvan.di.component.DaggerActivityComponent
import com.jam.mvvmdemojaiminsarvan.ui.base.UiState
import com.jam.mvvmdemojaiminsarvan.ui.topheadline.TopHeadlineAdapter
import com.jam.mvvmdemojaiminsarvan.ui.topheadline.TopHeadlineViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var topHeadlineViewModel: TopHeadlineViewModel

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setUPObserver()

    }

    fun setupUI(){
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(
            recyclerView.context,(recyclerView.layoutManager as LinearLayoutManager).orientation)
        )
        recyclerView.adapter = adapter
    }

    fun setUPObserver(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                topHeadlineViewModel.uiState.collect{
                    when (it) {
                        is UiState.Success -> {
                            binding.progressbar.visibility = View.GONE
                            rednderList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE

                        }

                        is UiState.Error -> {
                            binding.progressbar.visibility = View.GONE
                            Toast.makeText(this@MainActivity,it.message, Toast.LENGTH_LONG).show()
                        }
                        UiState.Loading -> {
                            binding.progressbar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE

                        }
                    }

                }
            }
        }
    }

    fun rednderList(articleList: List<Article>){
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies(){
        DaggerActivityComponent.builder().applicationComponent((application as MVVMApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }


}