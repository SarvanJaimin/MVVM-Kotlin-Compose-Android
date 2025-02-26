package com.jam.mvvmdemojaiminsarvan.ui.topheadline

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jam.mvvmdemojaiminsarvan.data.models.Article
import com.jam.mvvmdemojaiminsarvan.databinding.TopHeadlineItemLayoutBinding
import java.util.ArrayList

class TopHeadlineAdapter(private val articleList: ArrayList<Article>) : RecyclerView.Adapter<TopHeadlineAdapter.DataViewHolder>() {


    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source?.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.urlToImage)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(binding.imageViewBanner.context, Uri.parse(article.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  DataViewHolder(TopHeadlineItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(articleList[position])

    override fun getItemCount(): Int = articleList.size

    fun addData(list: List<Article>){
        articleList.addAll(list)
    }

}