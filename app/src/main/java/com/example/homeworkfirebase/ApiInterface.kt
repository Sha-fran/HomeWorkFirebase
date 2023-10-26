package com.example.homeworkfirebase

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/maps/api/directions/json?destination=49.553516,25.594767&origin=49.842957,24.031111&key=AIzaSyByggCLrYxBcp_jAbHLTX1zgVhwcjPM8nQ")
    suspend fun getSimpleRoute():Response<DirectionResponce>
}
