package com.example.nazariy.geekapps.domain.interfaces

import com.example.nazariy.geekapps.domain.model.rss.Result

interface ILocalRepository {
    fun getFavourites(): List<Result>
    fun saveFavourite(favourite: Result)
    fun getAudioBooks(): List<Result>
    fun getMovies(): List<Result>
    fun getPodcasts(): List<Result>
    fun saveResults(results: List<Result>)
}