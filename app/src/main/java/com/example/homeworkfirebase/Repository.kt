package com.example.homeworkfirebase

class Repository {
    private val api = ApiClient.client.create(ApiInterface::class.java)

    suspend fun getRoute() = api.getSimpleRoute()
}
