package com.example.nasaapod.Data

import retrofit2.http.GET
import retrofit2.http.Query


interface ApodService {
    @GET("planetary/apod")
    suspend fun getApod(@Query("api_key") apiKey: String): ApodModel
}