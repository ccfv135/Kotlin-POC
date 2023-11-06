package com.app.koltinpoc.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.koltinpoc.R
import com.app.koltinpoc.databinding.AdapterNewsItemBinding
import com.app.koltinpoc.model.AnimeInfo
import com.app.koltinpoc.utils.loadImageFromGlide
import javax.inject.Inject

class NewsAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterNewsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<AnimeInfo>() {
        override fun areItemsTheSame(oldItem: AnimeInfo, newItem: AnimeInfo): Boolean {
            // Comparar elementos individuales en la lista data por su malId
            return oldItem.data.zip(newItem.data).all { (old, new) -> old.malId == new.malId }
        }

        override fun areContentsTheSame(oldItem: AnimeInfo, newItem: AnimeInfo): Boolean {
            // Comparar objetos AnimeInfo completos para verificar igualdad
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AdapterNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animeInfo = differ.currentList[position]

        // Verificar si la lista 'data' tiene elementos antes de intentar acceder a ellos
        if (position < animeInfo.data.size) {
            val animeData = animeInfo.data[position]

            holder.binding.apply {
                ivArticle.loadImageFromGlide(animeData.images.jpg.imageUrl)
                tvTitle.text = animeData.title
                tvDescription.text = animeData.source
                val comments = "${animeData.scoredBy} Users Scored"
                tvPublished.text = comments
                if (animeData.approved) {
                    tvSource.text = context.getString(R.string.read_post)
                }
            }

            holder.itemView.setOnClickListener {
                setAnimeInfoClickListener?.let {
                    it(animeInfo)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var setAnimeInfoClickListener: ((animeInfo: AnimeInfo) -> Unit)? = null

    fun onAnimeInfoClicked(listener: (AnimeInfo) -> Unit) {
        setAnimeInfoClickListener = listener
    }
}
