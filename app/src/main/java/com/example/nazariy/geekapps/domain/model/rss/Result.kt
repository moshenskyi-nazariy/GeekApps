package com.example.nazariy.geekapps.domain.model.rss

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
        var contentAdvisoryRating: String?)
