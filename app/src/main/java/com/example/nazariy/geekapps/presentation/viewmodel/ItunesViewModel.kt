package com.example.nazariy.geekapps.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.nazariy.geekapps.data.remote.RemoteRepository
import com.example.nazariy.geekapps.domain.model.lookup.DetailsResult
import com.example.nazariy.geekapps.domain.model.rss.Result
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

class ItunesViewModel : ViewModel() {
    val itunesItems = MutableLiveData<List<Result>>()
    val error = MutableLiveData<String>()
    val details: MutableLiveData<List<DetailsResult>> = MutableLiveData()

    fun loadAudiobooks() {
        val remoteRepository = RemoteRepository()
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, {
            val response = remoteRepository.getAudioBooks().await()
            if (response.isSuccessful) {
                itunesItems.postValue(response.body()?.feed?.results)
            } else {
                error.postValue(response.message())
            }
        })
    }

    fun loadMovies() {
        val remoteRepository = RemoteRepository()
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, {
            val response = remoteRepository.getMovies().await()
            if (response.isSuccessful) {
                itunesItems.postValue(response.body()?.feed?.results)
            } else {
                error.postValue(response.message())
            }
        })
    }

    fun loadPodcasts() {
        val remoteRepository = RemoteRepository()
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, {
            val response = remoteRepository.getPodcasts().await()
            if (response.isSuccessful) {
                itunesItems.postValue(response.body()?.feed?.results)
            } else {
                error.postValue(response.message())
            }
        })
    }

    fun loadDetails(id: String) {
        val remoteRepository = RemoteRepository()
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, {
            val response = remoteRepository.getDetails(id).await()
            if (response.isSuccessful) {
                details.postValue(response.body()?.results)
            } else {
                error.postValue(response.message())
            }
        })
    }

}