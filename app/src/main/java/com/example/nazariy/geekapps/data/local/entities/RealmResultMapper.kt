package com.example.nazariy.geekapps.data.local.entities

import com.example.nazariy.geekapps.domain.model.rss.Genre
import io.realm.RealmList

class RealmResultMapper {
    companion object {
        fun mapGenres(genres: List<Genre>?): RealmList<GenreRealm> {
            val realmList = RealmList<GenreRealm>()
            if (genres != null) {
                for (genre in genres) {
                    val genreRealm = GenreRealm(genre)
                    realmList.add(genreRealm)
                }
            }
            return realmList
        }
    }

}
