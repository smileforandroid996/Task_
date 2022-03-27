package com.mohamed.task.data.network

import com.mohamed.task.data.entities.UsersRemote
import retrofit2.http.GET

interface ServiceApi {

    // Users API
    @GET("users")
    suspend fun getUsers(): List<UsersRemote>


}