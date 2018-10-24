package com.example.nazariy.geekapps.domain.interfaces

import com.example.nazariy.geekapps.domain.model.rss.Result

interface ILocalRepository {
    fun getFavourites(): List<Result>
    fun saveFavourites(results: List<Result>)
}