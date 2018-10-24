package com.example.nazariy.geekapps.data.local

import com.example.nazariy.geekapps.domain.interfaces.AbstractRepository
import com.example.nazariy.geekapps.domain.interfaces.ILocalRepository
import com.example.nazariy.geekapps.domain.model.lookup.DetailsResponse
import com.example.nazariy.geekapps.domain.model.rss.ItunesModel
import com.example.nazariy.geekapps.domain.model.rss.Result
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response

class LocalRepository: AbstractRepository, ILocalRepository {
    override fun getFavourites(): List<Result> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveFavourites(results: List<Result>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAudioBooks(): Deferred<Response<ItunesModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovies(): Deferred<Response<ItunesModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPodcasts(): Deferred<Response<ItunesModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDetails(id: String): Deferred<Response<DetailsResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}