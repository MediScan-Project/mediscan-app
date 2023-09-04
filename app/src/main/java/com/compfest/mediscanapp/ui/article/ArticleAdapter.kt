package com.compfest.mediscanapp.ui.article

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.compfest.mediscanapp.api.response.DataArtikelItem
import com.compfest.mediscanapp.databinding.ItemArticleBinding

class ArticleAdapter : PagingDataAdapter<DataArtikelItem, ArticleAdapter.ListViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    inner class ListViewHolder(var binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userData: DataArtikelItem) {
            binding.apply {
                Glide.with(itemView)
                    .load(userData.image)
                    .into(binding.imgItemArticle)
                binding.titleArticle.text = userData.title
                binding.contentArticle.text = userData.content
            }
            itemView.setOnClickListener{
                val intentToDetail = Intent(itemView.context, DetailArticleActivity::class.java)
                intentToDetail.putExtra(DetailArticleActivity.NAME_EXTRA, userData.title)
                intentToDetail.putExtra(DetailArticleActivity.DESCRIPTION_EXTRA, userData.content)
                intentToDetail.putExtra(DetailArticleActivity.PHOTO_EXTRA, userData.image)
                intentToDetail.putExtra(DetailArticleActivity.LINK_EXTRA, userData.link)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataArtikelItem>() {
            override fun areItemsTheSame(
                oldItem: DataArtikelItem,
                newItem: DataArtikelItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataArtikelItem,
                newItem: DataArtikelItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}