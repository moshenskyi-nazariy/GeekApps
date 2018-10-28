package com.example.nazariy.geekapps.domain.model.rss

import com.example.nazariy.geekapps.data.local.entities.ResultRealm

data class Result(
        var artistName: String?,
        var id: String?,
        var releaseDate: String?,
        var name: String?,
        var kind: String?,
        var copyright: String?,
        var artistId: String?,
        var artistUrl: String?,
        var artworkUrl100: String?,
        var genres: List<Genre>?,
        var url: String?,
        var contentAdvisoryRating: String?,
        var isChecked: Boolean?) {
    constructor(resultRealm: ResultRealm) : this(resultRealm.artistName, resultRealm.id,
            resultRealm.releaseDate, resultRealm.name, resultRealm.kind, resultRealm.copyright,
            resultRealm.artistId, resultRealm.artistUrl, resultRealm.artworkUrl100,
            ResultMapper.mapGenres(resultRealm.genres), resultRealm.url,
            resultRealm.contentAdvisoryRating, resultRealm.isChecked)
}
