package com.example.nazariy.geekapps.data.api

import com.example.nazariy.geekapps.domain.model.ItunesModel
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("audiobooks/top-audiobooks/all/25/explicit.json")
    fun getAudiobooks(): Deferred<Response<ItunesModel>>
}