package com.example.nazariy.geekapps.presentation.view

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.nazariy.geekapps.databinding.ItunesListItemBinding
import com.example.nazariy.geekapps.domain.model.rss.Result
import com.example.nazariy.geekapps.presentation.view.fragments.AdapterClickListener

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    val context = imageView.context
    Glide.with(context).load(url).into(imageView)
}

class ItunesItemAdapter(private val itemClickListener: AdapterClickListener, private val likeIconClickListener: OnFavouriteAdded)
    : RecyclerView.Adapter<ItunesItemAdapter.ItunesItemViewHolder>() {
    private var results: List<Result> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItunesItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItunesListItemBinding.inflate(layoutInflater,
                parent, false)
        return ItunesItemViewHolder(itemBinding)
    }

    override fun getItemCount() = results.size


    override fun onBindViewHolder(holder: ItunesItemViewHolder, position: Int) {
        holder.bind(results[position])
    }

    fun update(results: List<Result>) {
        this.results += results
        notifyDataSetChanged()
    }

    inner class ItunesItemViewHolder(private val itemBinding: ItunesListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(result: Result) {
            itemBinding.model = result
            itemBinding.executePendingBindings()

            itemView.setOnClickListener {
                itemClickListener.onClick(result.id)
            }

            itemBinding.likeIcon.setOnCheckedChangeListener { _, isChecked ->
                result.isChecked = isChecked
                likeIconClickListener.onFavouriteItemChanged(result)
            }
        }
    }

    interface OnFavouriteAdded {
        fun onFavouriteItemChanged(result: Result)
    }
}