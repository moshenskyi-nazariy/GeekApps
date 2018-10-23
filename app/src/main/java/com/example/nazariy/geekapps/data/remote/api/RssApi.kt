package com.example.nazariy.geekapps.data.remote.api

import com.example.nazariy.geekapps.domain.model.rss.ItunesModel
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface RssApi {
    @GET("audiobooks/top-audiobooks/all/25/explicit.json")
    fun getAudiobooks(): Deferred<Response<ItunesModel>>

    @GET("movies/top-movies/all/25/explicit.json")
    fun getMovies(): Deferred<Response<ItunesModel>>

    @GET("podcasts/top-podcasts/all/25/explicit.json")
    fun getPodcasts(): Deferred<Response<ItunesModel>>
}