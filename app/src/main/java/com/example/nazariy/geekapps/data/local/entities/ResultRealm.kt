package com.example.nazariy.geekapps.data.local.entities

import com.example.nazariy.geekapps.domain.model.rss.Result
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ResultRealm(
        var artistName: String?,
        @PrimaryKey var id: String?,
        var releaseDate: String?,
        var name: String?,
        var kind: String?,
        var copyright: String?,
        var artistId: String?,
        var artistUrl: String?,
        var artworkUrl100: String?,
        var genres: RealmList<GenreRealm>?,
        var url: String?,
        var contentAdvisoryRating: String?) : RealmObject() {
    constructor() : this(null, null, null, null, null,
            null, null, null, null, null,
            null, null)
    constructor(result: Result) :
        this(result.artistName, result.id, result.releaseDate, result.name, result.kind,
                result.copyright, result.artistId, result.artistUrl, result.artworkUrl100,
                RealmResultMapper.mapGenres(result.genres), result.url, result.contentAdvisoryRating)
}