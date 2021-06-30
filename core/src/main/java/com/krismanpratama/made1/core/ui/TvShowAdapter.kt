package com.krismanpratama.made1.core.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.krismanpratama.made1.core.R
import com.krismanpratama.made1.core.databinding.ItemCardBinding
import com.krismanpratama.made1.core.domain.model.TvShowModelDomain
import com.krismanpratama.made1.core.utils.Constants.BASE_POSTER_URL

class TvShowAdapter(private val context: Context) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val listData = ArrayList<TvShowModelDomain>()
    var onItemClick: ((TvShowModelDomain) -> Unit)? = null


    inner class TvShowViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(tvShowParam: TvShowModelDomain){
            with(binding){
                title.text = tvShowParam.original_name
                Glide.with(itemView.context)
                        .load(BASE_POSTER_URL + tvShowParam.poster_path)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(picture)
            }


        }

        init{
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    fun setData(tvShows: List<TvShowModelDomain>?){
        if (tvShows == null) return
        listData.clear()
        listData.addAll(tvShows)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsTvShowBinding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return TvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listData[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listData.size

}
