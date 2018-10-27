package com.example.nazariy.geekapps.domain.model.rss

import com.example.nazariy.geekapps.data.local.entities.GenreRealm
import io.realm.RealmList

class ResultMapper {
    companion object {
        fun mapGenres(genres: RealmList<GenreRealm>?): List<Genre> {
            val realmList = ArrayList<Genre>()
            genres?.let {
                for (genreRealm in genres) {
                    val genre = Genre(genreRealm)
                    realmList.add(genre)
                }
            }
            return realmList
        }
    }
}
