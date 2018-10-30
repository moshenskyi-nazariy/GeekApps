package com.example.nazariy.geekapps.data.local

import com.example.nazariy.geekapps.data.local.entities.ResultRealm
import com.example.nazariy.geekapps.domain.model.rss.Result
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

private const val FIELD_IS_CHECKED = "isChecked"
private const val FIELD_KIND = "kind"
private const val FIELD_KIND_MOVIE = "movie"
private const val FIELD_KIND_PODCAST = "podcast"
private const val FIELD_KIND_AUDIOBOOK = "book"

class ResultDao(private val realmDb: Realm) {
    fun saveFavourites(favourite: Result) {
        realmDb.apply {
            beginTransaction()
            val realmResult = ResultRealm(favourite)
            copyToRealmOrUpdate(realmResult)
            commitTransaction()
        }
    }

    fun getFavourites(): List<Result> {
        var results: RealmResults<ResultRealm>? = null

        realmDb.apply {
            beginTransaction()
            results = where(ResultRealm::class.java)
                        .equalTo(FIELD_IS_CHECKED, true)
                        .sort(FIELD_KIND)
                        .findAll()
            commitTransaction()
        }
        return getList(results)
    }

    private fun getList(realmList: RealmResults<ResultRealm>?): List<Result> {
        val realmResultList = realmList?.toList()
        val resultList = ArrayList<Result>()
        realmResultList?.let {
            for (result in realmResultList) {
                val domainResultEntity = Result(result)
                resultList.add(domainResultEntity)
            }
        }

        return resultList
    }

    fun getAudioBooks(): List<Result> {
        var results: RealmResults<ResultRealm>? = null

        realmDb.apply {
            beginTransaction()
            results = where(ResultRealm::class.java)
                    .equalTo(FIELD_KIND, FIELD_KIND_AUDIOBOOK)
                    .findAll()
            commitTransaction()
        }

        return getList(results)
    }

    fun getMovies(): List<Result> {
        var results: RealmResults<ResultRealm>? = null

        realmDb.apply {
            beginTransaction()
            results = where(ResultRealm::class.java)
                        .equalTo(FIELD_KIND, FIELD_KIND_MOVIE)
                        .findAll()
            commitTransaction()
        }
        return getList(results)
    }

    fun getPodcasts(): List<Result> {
        var results: RealmResults<ResultRealm>? = null

        realmDb.apply {
            beginTransaction()
            results = where(ResultRealm::class.java)
                        .equalTo(FIELD_KIND, FIELD_KIND_PODCAST)
                        .findAll()
            commitTransaction()
        }
        return getList(results)
    }

    fun saveResults(results: List<Result>) {
        realmDb.apply {
            beginTransaction()
            for (result in results) {
                copyToRealmOrUpdate(ResultRealm(result))
            }
            commitTransaction()
        }
    }
}