package com.example.nazariy.geekapps.domain.interfaces

import com.example.nazariy.geekapps.domain.model.ItunesModel
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response

interface AbstractRepository {
    fun getAudioBooks(): Deferred<Response<ItunesModel>>

    fun getMovies(): Deferred<Response<ItunesModel>>

    fun getPodcasts(): Deferred<Response<ItunesModel>>
}