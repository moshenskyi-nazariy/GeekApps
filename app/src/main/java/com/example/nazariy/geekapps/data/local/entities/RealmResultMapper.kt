package com.example.nazariy.geekapps.data.local.entities

import com.example.nazariy.geekapps.domain.model.rss.Genre
import io.realm.RealmList

fun mapGenres(genres: List<Genre>?): RealmList<GenreRealm> = RealmList<GenreRealm>().apply {
    genres?.map(::GenreRealm)?.let(::addAll)
}

