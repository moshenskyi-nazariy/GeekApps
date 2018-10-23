package com.example.nazariy.geekapps.data.remote.api

import com.example.nazariy.geekapps.domain.model.lookup.DetailsResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LookupApi {
    @GET()
    fun getDetails(@Query("id") id: String): Deferred<Response<DetailsResponse>>
}