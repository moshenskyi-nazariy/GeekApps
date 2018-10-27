package com.example.nazariy.geekapps.data.local.entities

import com.example.nazariy.geekapps.domain.model.rss.Genre
import io.realm.RealmObject

open class GenreRealm(var genreId: String?,
                      var name: String?,
                      var url: String?) : RealmObject() {
    constructor() : this(null, null, null)
    constructor(genre: Genre) : this(genre.genreId, genre.name, genre.url)
}