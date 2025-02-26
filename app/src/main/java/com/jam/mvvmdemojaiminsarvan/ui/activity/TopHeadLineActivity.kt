package com.jam.mvvmdemojaiminsarvan.ui.activity

import android.os.Bundle
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.jam.mvvmdemojaiminsarvan.MVVMApplication
import com.jam.mvvmdemojaiminsarvan.data.models.Article
import com.jam.mvvmdemojaiminsarvan.di.ActivityModule
import com.jam.mvvmdemojaiminsarvan.di.component.DaggerActivityComponent
import com.jam.mvvmdemojaiminsarvan.ui.topheadline.TopHeadlinePagerViewModel
import javax.inject.Inject

class TopHeadLineActivity : ComponentActivity() {

    @Inject
    lateinit var topHeadlineViewModel: TopHeadlinePagerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContent {
          GETData()
        }
    }

    @Composable
    fun EachRow(article: Article) {
        // TODO: Implement each row item with compose
        Column(
            modifier = Modifier.padding(4.dp).fillMaxSize()
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


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun GETData() {
        val data = topHeadlineViewModel.topHeadlines.collectAsLazyPagingItems()

        LazyColumn {
            items(data.itemCount)
            { index ->
                val article = data[index]
                if (article != null) {
                    EachRow(article)
                }
            }
            data.apply {
                when {
                    loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                        item { CircularProgressIndicator() }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item { Text("error loading") }
                    }
                }
            }

        }
    }

    private fun injectDependencies(){
        DaggerActivityComponent.builder().applicationComponent((application as MVVMApplication).applicationComponent)
            .activityModule(ActivityModule(this@TopHeadLineActivity)).build().inject(this@TopHeadLineActivity)
    }

}