package com.example.nazariy.geekapps.presentation.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.nazariy.geekapps.R
import com.example.nazariy.geekapps.domain.model.Result
import com.example.nazariy.geekapps.presentation.viewmodel.ItunesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var itunesViewModel: ItunesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
