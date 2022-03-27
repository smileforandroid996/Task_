package com.mohamed.task.data.repository


import com.mohamed.task.data.mapper.UserMapper
import com.mohamed.task.data.source.UsersDS
import com.mohamed.task.domain.entities.UserEntity
import com.mohamed.task.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class UsersRepositoryImpl @Inject constructor(private val usersDS: UsersDS, @Named("user") private val userMapper: UserMapper) : UserRepository{

    override fun geUsers(): Flow<List<UserEntity>> {
        return flow {
            emit(usersDS.getUsers().map { userMapper.mapFromRemoteToEntity(it) })
        }
    }

}