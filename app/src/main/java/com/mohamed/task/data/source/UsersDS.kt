package com.mohamed.task.data.source

import com.mohamed.task.data.entities.UsersRemote
import com.mohamed.task.data.network.ServiceApi
import javax.inject.Inject

class UsersDS  @Inject constructor(private val serviceApi: ServiceApi) {
    suspend fun getUsers(): List<UsersRemote> {
        return serviceApi.getUsers()
    }
}