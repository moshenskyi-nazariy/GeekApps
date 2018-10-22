package com.example.nazariy.geekapps.presentation.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.nazariy.geekapps.R
import com.example.nazariy.geekapps.databinding.ItunesListItemBinding
import com.example.nazariy.geekapps.domain.model.Result
import com.example.nazariy.geekapps.presentation.viewmodel.ItunesViewModel

class ItunesItemsFragment : Fragment() {
    private lateinit var itunesViewModel: ItunesViewModel
    private lateinit var itunesItemAdapter: ItunesItemAdapter
    private lateinit var itunesList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_itunes_items, container, false)
        initUi(root)
        initViewModel()
        itunesViewModel.loadAudiobooks()
        return root
    }

    private fun initUi(root: View) {
        itunesList = root.findViewById(R.id.itunes_list) as RecyclerView

        itunesItemAdapter = ItunesItemAdapter()
        itunesList.adapter = itunesItemAdapter

        itunesList.layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL,
                false)
    }

    private fun initViewModel() {
        itunesViewModel = ViewModelProviders.of(this)
                .get(ItunesViewModel::class.java)
        itunesViewModel.error.observe(this, Observer { value ->
            value?.let { showMessage(value) }
        })

        itunesViewModel.audioBooks.observe(this, Observer { value ->
            value?.let { obtainResult(value) }
        })
    }

    private fun obtainResult(result: List<Result>) {
        itunesItemAdapter.update(result)
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}
