package com.example.nazariy.geekapps.domain.model.rss

import io.realm.RealmObject

data class Genre(
        var genreId: String?,
        var name: String?,
        var url: String?) : RealmObject() {
    constructor() : this(null, null, null)
}
