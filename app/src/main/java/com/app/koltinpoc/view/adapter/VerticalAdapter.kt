package com.app.koltinpoc.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.koltinpoc.databinding.AdapterVerticalItemBinding
import com.app.koltinpoc.model.AnimeData
import com.app.koltinpoc.model.AnimeInfo
import com.app.koltinpoc.utils.loadImageFromGlide
import javax.inject.Inject

class VerticalAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterVerticalItemBinding) :
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
            AdapterVerticalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {

            imageViewVerticalItem.loadImageFromGlide(item.images.jpg.imageUrl)
            liveStateSeason.text = if (item.airing) "Airing" else "Not Airing"
            titleSeason.text = item.title
            episodeSeason.text = item.episodes.toString()
            dateSeason.text = item.aired.from
        }
        holder.binding.executePendingBindings()

        holder.itemView.setOnClickListener {
            setAnimeInfoClickListener?.let {
                it(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var setAnimeInfoClickListener: ((animeInfo: AnimeData) -> Unit)? = null

    fun onAnimeInfoClicked(listener: (AnimeData) -> Unit) {
        setAnimeInfoClickListener = listener
    }
}
