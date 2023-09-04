package com.compfest.mediscanapp.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.compfest.mediscanapp.api.response.PredictionsItemItem
import com.compfest.mediscanapp.databinding.ItemDrugBinding

class ResultModelAdapter(private val listStory: List<PredictionsItemItem>) : RecyclerView.Adapter<ResultModelAdapter.ListViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemDrugBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listStory[position])
    }

    override fun getItemCount(): Int = listStory.size

    inner class ListViewHolder(var binding: ItemDrugBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userData: PredictionsItemItem) {
            binding.apply {
//                Glide.with(itemView)
//                    .load(userData.photoUrl).circleCrop()
//                    .into(binding.imgItemPhoto)
                binding.tvItemName.text = userData.className
                binding.tvItemDescription.text = userData.confidence
            }
//            itemView.setOnClickListener{
//                val intentToDetail = Intent(itemView.context, DetailStoryActivity::class.java)
//                intentToDetail.putExtra(DetailStoryActivity.NAME_EXTRA, userData.name)
//                intentToDetail.putExtra(DetailStoryActivity.DESCRIPTION_EXTRA, userData.description)
//                intentToDetail.putExtra(DetailStoryActivity.PHOTO_EXTRA, userData.photoUrl)
//                itemView.context.startActivity(intentToDetail)
//            }

        }
    }
}