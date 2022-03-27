package com.mohamed.task.domain.usecases


import com.mohamed.task.domain.entities.UserEntity
import com.mohamed.task.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun build(): Flow<List<UserEntity>> {
        return userRepository.geUsers()
    }
}