package com.example.nazariy.geekapps.domain.model.rss

import com.example.nazariy.geekapps.data.local.entities.GenreRealm

data class Genre(
        var genreId: String?,
        var name: String?,
        var url: String?) {
    constructor(genreRealm: GenreRealm) : this(genreRealm.genreId, genreRealm.name,
            genreRealm.url)
}