package com.example.nazariy.geekapps.presentation.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.nazariy.geekapps.domain.model.Result
import com.example.nazariy.geekapps.presentation.view.ItunesItemAdapter
import com.example.nazariy.geekapps.presentation.viewmodel.ItunesViewModel

open class ItunesFragment: Fragment() {
    protected lateinit var itunesViewModel: ItunesViewModel
    protected lateinit var itunesItemAdapter: ItunesItemAdapter
    protected lateinit var itunesList: RecyclerView

    protected fun initViewModel() {
        itunesViewModel = ViewModelProviders.of(this)
                .get(ItunesViewModel::class.java)
        itunesViewModel.error.observe(this, Observer { value ->
            value?.let { showMessage(value) }
        })

        itunesViewModel.audioBooks.observe(this, Observer { value ->
            value?.let { obtainResult(value) }
        })
    }

    protected open fun initUi(root: View) {
        itunesItemAdapter = ItunesItemAdapter()
        itunesList.adapter = itunesItemAdapter

        itunesList.layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL,
                false)
    }

    private fun obtainResult(result: List<Result>) {
        itunesItemAdapter.update(result)
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}