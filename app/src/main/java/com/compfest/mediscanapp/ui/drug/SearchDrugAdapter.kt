package com.compfest.mediscanapp.ui.drug

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.compfest.mediscanapp.api.response.DataItem
import com.compfest.mediscanapp.databinding.ItemDrugBinding
import com.compfest.mediscanapp.ui.resultdrug.ResultDrugActivity


class SearchDrugAdapter : PagingDataAdapter<DataItem, SearchDrugAdapter.ListViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemDrugBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    inner class ListViewHolder(var binding: ItemDrugBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userData: DataItem) {
            binding.apply {
//                Glide.with(itemView)
//                    .load(userData.).circleCrop()
//                    .into(binding.imageView2)
                binding.tvItemName.text = userData.namaObat
                binding.tvItemDescription.text = userData.golonganObat
            }
            itemView.setOnClickListener{
                val intentToDetail = Intent(itemView.context, ResultDrugActivity::class.java)
                intentToDetail.putExtra(ResultDrugActivity.NAME_EXTRA, userData.namaObat)
                intentToDetail.putExtra(ResultDrugActivity.DESCRIPTION_EXTRA, userData.deskripsi)
                intentToDetail.putExtra(ResultDrugActivity.RESEP_EXTRA, userData.resepDokter)
                intentToDetail.putExtra(ResultDrugActivity.DOSIS_EXTRA, userData.dosis)
                intentToDetail.putExtra(ResultDrugActivity.KOMPOSISI_EXTRA, userData.komposisi)
                intentToDetail.putExtra(ResultDrugActivity.ATURAN_EXTRA, userData.penyajian)
                intentToDetail.putExtra(ResultDrugActivity.MANFAAT_EXTRA, userData.manfaat)
                intentToDetail.putExtra(ResultDrugActivity.GOLONGAN_EXTRA, userData.golonganObat)

                itemView.context.startActivity(intentToDetail)
            }

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem.namaObat == newItem.namaObat
            }
        }
    }
}