package com.mohamed.task.data.mapper

import com.mohamed.task.data.entities.UsersRemote
import com.mohamed.task.domain.entities.UserEntity


class UserMapper : MapFromRemoteToEntity<UsersRemote, UserEntity> {
    override fun mapFromRemoteToEntity(model: UsersRemote): UserEntity {
        with(model) {
            return UserEntity(
                avatarUrl = avatarUrl,
                login = login,
                htmlUrl = htmlUrl
            )
        }
    }
}