package com.example.nazariy.geekapps.presentation.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nazariy.geekapps.R
import com.example.nazariy.geekapps.domain.model.Result
import kotlinx.android.synthetic.main.itunes_list_item.view.*

class ItunesItemAdapter(val results: List<Result>) : RecyclerView.Adapter<ItunesItemAdapter.ItunesItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItunesItemViewHolder {
        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.itunes_list_item, parent, false)
        return ItunesItemViewHolder(root)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: ItunesItemViewHolder, position: Int) {
        holder.artistName.text = results[position].artistName
    }

    class ItunesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistName = itemView.artist_name!!
    }
}