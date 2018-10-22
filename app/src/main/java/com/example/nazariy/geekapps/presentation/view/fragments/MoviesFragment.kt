package com.example.nazariy.geekapps.presentation.view.fragments

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nazariy.geekapps.R

class MoviesFragment : ItunesFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_itunes_items, container, false)
        initUi(root)
        initViewModel()
        itunesViewModel.loadMovies()
        return root
    }

    override fun initUi(root: View) {
        itunesList = root.findViewById(R.id.audiobooks_list) as RecyclerView

        super.initUi(root)
    }

}
