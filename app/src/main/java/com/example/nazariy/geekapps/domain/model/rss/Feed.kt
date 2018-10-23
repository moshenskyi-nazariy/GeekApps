package com.example.nazariy.geekapps.domain.model.rss

data class Feed(
        var title: String?,
        var id: String?,
        var author: Author?,
        var links: List<Link>?,
        var copyright: String?,
        var country: String?,
        var icon: String?,
        var updated: String?,
        var results: List<Result>?)
