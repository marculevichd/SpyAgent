package com.example.spyagent.data

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/66HHqc")
    abstract suspend fun getSetDataFromJson(): Response<SetDataResponse>
}