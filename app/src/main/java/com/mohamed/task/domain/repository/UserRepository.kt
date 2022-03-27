package com.mohamed.task.domain.repository

import com.mohamed.task.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun geUsers(): Flow<List<UserEntity>>
}