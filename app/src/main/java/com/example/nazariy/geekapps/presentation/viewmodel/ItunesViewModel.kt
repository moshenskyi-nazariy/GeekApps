package com.example.nazariy.geekapps.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.nazariy.geekapps.data.local.LocalRepository
import com.example.nazariy.geekapps.data.remote.RemoteRepository
import com.example.nazariy.geekapps.domain.model.lookup.DetailsResult
import com.example.nazariy.geekapps.domain.model.rss.ItunesModel
import com.example.nazariy.geekapps.domain.model.rss.Result
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import retrofit2.Response
import java.io.IOException

private const val NO_DATA_FOUND = "No data found"

class ItunesViewModel : ViewModel() {
    val itunesItems = MutableLiveData<List<Result>>()
    val error = MutableLiveData<String>()
    val details: MutableLiveData<DetailsResult> = MutableLiveData()

    private val remoteRepository = RemoteRepository()
    private val localRepository = LocalRepository()

    fun loadAudiobooks() {
        val localResponse = localRepository.getAudioBooks()

        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, {
            if (localResponse.isEmpty()) {
                try {
                    val response = remoteRepository.getAudioBooks().await()
                    getDataFromWebAndShowResult(response)
                } catch (exception: IOException) {
                    error.postValue(NO_DATA_FOUND)
                }
            } else {
                itunesItems.postValue(localResponse)
            }

        })
    }

    fun loadFavourites() {
        val localResponse = localRepository.getFavourites()

        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, {
            if (localResponse.isEmpty()) {
                error.postValue(NO_DATA_FOUND)
            } else {
                itunesItems.postValue(localResponse)
            }

        })
    }

    fun saveFavourite(result: Result) {
        localRepository.saveFavourite(result)
    }

    private fun getDataFromWebAndShowResult(response: Response<ItunesModel>) {
        if (response.isSuccessful) {
            val results = response.body()?.feed?.results
            if (results != null) {
                GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT, {
                    localRepository.saveResults(results)
                })
            }
            itunesItems.postValue(results)
        } else {
            error.postValue(response.message())
        }
    }

    fun loadMovies() {
        val localResponse = localRepository.getMovies()

        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, {
            if (localResponse.isEmpty()) {
                try {
                    val response = remoteRepository.getMovies().await()
                    getDataFromWebAndShowResult(response)
                } catch (exception: IOException) {
                    error.postValue(NO_DATA_FOUND)
                }
            } else {
                itunesItems.postValue(localResponse)
            }
        })
    }

    fun loadPodcasts() {
        val localResponse = localRepository.getPodcasts()

        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, {
            if (localResponse.isEmpty()) {
                try {
                    val response = remoteRepository.getPodcasts().await()
                    getDataFromWebAndShowResult(response)
                } catch (exception: IOException) {
                    error.postValue(NO_DATA_FOUND)
                }
            } else {
                itunesItems.postValue(localResponse)
            }
        })
    }

    fun loadDetails(id: String) {
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, {
            try {
                val response = remoteRepository.getDetails(id).await()
                if (response.isSuccessful) {
                    details.postValue(response.body()?.results?.get(0))
                } else {
                    error.postValue(response.message())
                }
            } catch (exception: IOException) {
                error.postValue(NO_DATA_FOUND)
            }
        })
    }

}