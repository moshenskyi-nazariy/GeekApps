package com.example.nazariy.geekapps.data.remote

import com.example.nazariy.geekapps.BuildConfig
import com.example.nazariy.geekapps.data.remote.api.Api
import com.example.nazariy.geekapps.domain.interfaces.AbstractRepository
import com.example.nazariy.geekapps.domain.model.ItunesModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepository : AbstractRepository {

    private var api: Api

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        api = retrofit.create(Api::class.java)
    }

    override fun getAudioBooks(): Deferred<Response<ItunesModel>> {
        return api.getAudiobooks()
    }

    override fun getMovies(): Deferred<Response<ItunesModel>> {
        return api.getMovies()
    }

    override fun getPodcasts(): Deferred<Response<ItunesModel>> {
        return api.getPodcasts()
    }
}