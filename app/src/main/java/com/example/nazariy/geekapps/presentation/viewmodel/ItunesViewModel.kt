package com.example.nazariy.geekapps.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.nazariy.geekapps.data.remote.RemoteRepository
import com.example.nazariy.geekapps.domain.model.Result
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

class ItunesViewModel : ViewModel() {
    val audioBooks = MutableLiveData<List<Result>>()
    val error = MutableLiveData<String>()

    fun loadAudiobooks() {
        val remoteRepository = RemoteRepository()
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, null, {
            val response = remoteRepository.getAudioBooks().await()
            if (response.isSuccessful) {
                audioBooks.postValue(response.body()?.feed?.results)
            } else {
                error.postValue(response.message())
            }
        })
    }

    fun loadMovies() {
        val remoteRepository = RemoteRepository()
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, null, {
            val response = remoteRepository.getMovies().await()
            if (response.isSuccessful) {
                audioBooks.postValue(response.body()?.feed?.results)
            } else {
                error.postValue(response.message())
            }
        })
    }

    fun loadPodcasts() {
        val remoteRepository = RemoteRepository()
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, null, {
            val response = remoteRepository.getPodcasts().await()
            if (response.isSuccessful) {
                audioBooks.postValue(response.body()?.feed?.results)
            } else {
                error.postValue(response.message())
            }
        })
    }
}