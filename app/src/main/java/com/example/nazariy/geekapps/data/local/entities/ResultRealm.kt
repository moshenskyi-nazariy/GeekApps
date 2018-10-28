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
        var contentAdvisoryRating: String?,
        var isChecked: Boolean?) : RealmObject() {
    constructor() : this(null, null, null, null, null,
            null, null, null, null, null,
            null, null, false)

    constructor(result: Result) :
            this(result.artistName, result.id, result.releaseDate, result.name, result.kind,
                    result.copyright, result.artistId, result.artistUrl, result.artworkUrl100,
                    RealmResultMapper.mapGenres(result.genres), result.url,
                    result.contentAdvisoryRating, result.isChecked)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ResultRealm) return false

        if (artistName != other.artistName) return false
        if (id != other.id) return false
        if (releaseDate != other.releaseDate) return false
        if (name != other.name) return false
        if (kind != other.kind) return false
        if (copyright != other.copyright) return false
        if (artistId != other.artistId) return false
        if (artistUrl != other.artistUrl) return false
        if (artworkUrl100 != other.artworkUrl100) return false
        if (genres != other.genres) return false
        if (url != other.url) return false
        if (contentAdvisoryRating != other.contentAdvisoryRating) return false

        return true
    }

    override fun hashCode(): Int {
        var result = artistName?.hashCode() ?: 0
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (releaseDate?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (kind?.hashCode() ?: 0)
        result = 31 * result + (copyright?.hashCode() ?: 0)
        result = 31 * result + (artistId?.hashCode() ?: 0)
        result = 31 * result + (artistUrl?.hashCode() ?: 0)
        result = 31 * result + (artworkUrl100?.hashCode() ?: 0)
        result = 31 * result + (genres?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (contentAdvisoryRating?.hashCode() ?: 0)
        return result
    }


}