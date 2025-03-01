package com.example.nasaapod.Data.repository

import com.example.nasaapod.Data.model.ApodModel
import com.example.nasaapod.Data.service.ApodService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApodRepository {
    private val apodService: ApodService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApodService::class.java)
    }

    suspend fun getApod(): ApodModel {
        return apodService.getApod("YOUR_API_KEY")
    }
}