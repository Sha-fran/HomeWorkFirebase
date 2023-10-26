package com.example.homeworkfirebase

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val client = Retrofit.Builder()
        .baseUrl("https://maps.googleapis.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}