package com.example.nasaapod.Data.service

import com.example.nasaapod.Data.model.ApodModel
import retrofit2.http.GET
import retrofit2.http.Query


interface ApodService {
    @GET("planetary/apod")
    suspend fun getApod(@Query("api_key") apiKey: String): ApodModel
}