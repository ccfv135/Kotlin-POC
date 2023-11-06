package com.app.koltinpoc.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.koltinpoc.databinding.AdapterHorizontalItemBinding
import com.app.koltinpoc.model.AnimeData
import com.app.koltinpoc.model.AnimeInfo
import com.app.koltinpoc.utils.loadImageFromGlide
import javax.inject.Inject

class HorizontalAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterHorizontalItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<AnimeData>() {
        override fun areItemsTheSame(oldItem: AnimeData, newItem: AnimeData): Boolean {
            return oldItem.malId == newItem.malId
        }

        override fun areContentsTheSame(oldItem: AnimeData, newItem: AnimeData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AdapterHorizontalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            ivHorizontalImage.loadImageFromGlide(item.images.jpg.imageUrl)
            tvHorizontalTitle.text = item.title
        }
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var setAnimeInfoClickListener: ((animeInfo: AnimeInfo) -> Unit)? = null

    fun onAnimeInfoClicked(listener: (AnimeInfo) -> Unit) {
        setAnimeInfoClickListener = listener
    }

}
