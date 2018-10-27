package com.example.nazariy.geekapps.data.local

import com.example.nazariy.geekapps.domain.interfaces.ILocalRepository
import com.example.nazariy.geekapps.domain.model.rss.Result
import io.realm.Realm

class LocalRepository : ILocalRepository {
    private val dao: ResultDao

    init {
        val realm = Realm.getDefaultInstance()
        dao = ResultDao(realm)
    }

    override fun getFavourites(): List<Result> {
        return dao.getFavourites()
    }

    override fun saveFavourites(favourite: Result) {
        dao.saveFavourites(favourite)
    }

    override fun getAudioBooks(): List<Result> {
        return dao.getAudioBooks()
    }

    override fun getMovies(): List<Result> {
        return dao.getMovies()
    }

    override fun getPodcasts(): List<Result> {
        return dao.getPodcasts()
    }

    override fun saveResults(results: List<Result>) {
        dao.saveResults(results)
    }
}