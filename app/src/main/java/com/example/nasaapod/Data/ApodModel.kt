package com.example.nasaapod.Data

import com.google.gson.annotations.SerializedName

data class ApodModel(
    val date: String,
    val explanation: String,
    val title: String,
    val url: String,
    @SerializedName("media_type") val mediaType: String,
    val copyright: String? = null
)