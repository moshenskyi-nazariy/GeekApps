package com.example.nazariy.geekapps.domain.interfaces

import com.example.nazariy.geekapps.domain.model.ItunesModel
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response

interface AbstractRepository {
    fun getAudioBook(): Deferred<Response<ItunesModel>>
}