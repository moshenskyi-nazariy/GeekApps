package com.example.nazariy.geekapps.data.local

import com.example.nazariy.geekapps.data.local.entities.ResultRealm
import com.example.nazariy.geekapps.domain.model.rss.Result
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

class ResultDao(private val realmDb: Realm) {
    fun saveFavourites(favourite: Result) {
        realmDb.beginTransaction()
        val realmResult = ResultRealm(favourite)
        realmDb.copyToRealmOrUpdate(realmResult)
        realmDb.commitTransaction()
    }

    fun getFavourites(): List<Result> {
        realmDb.beginTransaction()
        val results = realmDb.where(ResultRealm::class.java)
                .equalTo("isChecked", true)
                .sort("kind")
                .findAll()
        realmDb.commitTransaction()
        return getList(results)
    }

    private fun getList(realmList: RealmResults<ResultRealm>): List<Result> {
        val realmResultList = realmList.toList()
        val resultList = ArrayList<Result>()
        for (result in realmResultList) {
            val domainResultEntity = Result(result)
            resultList.add(domainResultEntity)
        }

        return resultList
    }

    fun getAudioBooks(): List<Result> {
        realmDb.beginTransaction()
        val results = realmDb
                .where(ResultRealm::class.java)
                .equalTo("kind", "book")
                .findAll()
        realmDb.commitTransaction()
        return getList(results)
    }

    fun getMovies(): List<Result> {
        realmDb.beginTransaction()
        val results = realmDb
                .where(ResultRealm::class.java)
                .equalTo("kind", "movie")
                .findAll()
        realmDb.commitTransaction()
        return getList(results)
    }

    fun getPodcasts(): List<Result> {
        realmDb.beginTransaction()
        val results = realmDb
                .where(ResultRealm::class.java)
                .equalTo("kind", "podcast")
                .findAll()
        realmDb.commitTransaction()
        return getList(results)
    }

    fun saveResults(results: List<Result>) {
        realmDb.beginTransaction()
        for (result in results) {
            realmDb.copyToRealmOrUpdate(ResultRealm(result))
        }
        realmDb.commitTransaction()
    }
}