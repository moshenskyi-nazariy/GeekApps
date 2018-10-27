package com.example.nazariy.geekapps.data.remote

import com.example.nazariy.geekapps.BuildConfig
import com.example.nazariy.geekapps.data.remote.api.LookupApi
import com.example.nazariy.geekapps.data.remote.api.RssApi
import com.example.nazariy.geekapps.domain.interfaces.IRemoteRepository
import com.example.nazariy.geekapps.domain.model.lookup.DetailsResponse
import com.example.nazariy.geekapps.domain.model.rss.ItunesModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepository : IRemoteRepository {

    private var rssApi: RssApi
    private var lookupApi: LookupApi

    override fun getAudioBooks(): Deferred<Response<ItunesModel>> {
        return rssApi.getAudiobooks()
    }

    override fun getMovies(): Deferred<Response<ItunesModel>> {
        return rssApi.getMovies()
    }

    override fun getPodcasts(): Deferred<Response<ItunesModel>> {
        return rssApi.getPodcasts()
    }

    override fun getDetails(id: String): Deferred<Response<DetailsResponse>> {
        return lookupApi.getDetails(id)
    }

    init {
        val baseRetrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        rssApi = baseRetrofit.create(RssApi::class.java)

        val lookupRetrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.DETAILS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        lookupApi = lookupRetrofit.create(LookupApi::class.java)
    }
}