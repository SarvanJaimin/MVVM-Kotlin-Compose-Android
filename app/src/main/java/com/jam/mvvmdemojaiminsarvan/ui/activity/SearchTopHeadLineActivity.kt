package com.jam.mvvmdemojaiminsarvan.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.jam.mvvmdemojaiminsarvan.MVVMApplication
import com.jam.mvvmdemojaiminsarvan.data.models.Article
import com.jam.mvvmdemojaiminsarvan.di.ActivityModule
import com.jam.mvvmdemojaiminsarvan.di.component.DaggerActivityComponent
import com.jam.mvvmdemojaiminsarvan.ui.base.UiState
import com.jam.mvvmdemojaiminsarvan.ui.topheadline.TopHeadlineSearchViewModel
import javax.inject.Inject


class SearchTopHeadLineActivity : ComponentActivity() {

    @Inject
   lateinit var topHeadlineSearchViewModel: TopHeadlineSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContent {
           Log.e("search=,","Hello")
        SearchScreen()
        }
    }


    @Preview
    @Composable
    fun SearchScreen(){
        // Add your SearchScreen UI here
        var query by remember { mutableStateOf("") }
       val uiState by topHeadlineSearchViewModel.uiState.collectAsStateWithLifecycle()

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            TextField(
                value = query,
                onValueChange = {
                    query = it
                    topHeadlineSearchViewModel.setSearchQuery(query)
                },
                label = { Text("Search News") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            when (uiState) {
                is UiState.Loading -> CircularProgressIndicator()
                is UiState.Success -> {
                    val articles = (uiState as UiState.Success<List<Article>>).data
                    LazyColumn {
                        items(articles) { articles ->
                            EachRow(articles)
                        }
                    }
                }

                is UiState.Error -> {
                    Text("Error: ${(uiState as UiState.Error).message}", color = Color.Red)
                }
            }
        }



    }

    @Composable
    fun EachRow(article: Article) {
        // TODO: Implement each row item with compose
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            Card(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                elevation = 2.dp,
                shape = RoundedCornerShape(4.dp)
            )
            {
                Box(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SubcomposeAsyncImage(model = article.urlToImage, loading = { CircularProgressIndicator() }, contentDescription = "Loading")
                }
            }
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier.padding(horizontal = 4.dp,vertical = 4.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = article.title?:"",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier.padding(horizontal = 4.dp,vertical = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = article.content?:"",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier.padding(horizontal = 4.dp,vertical = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(Color.Gray),
                text = article.author?:"",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }

    private fun injectDependencies(){
        DaggerActivityComponent.builder().applicationComponent((application as MVVMApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}