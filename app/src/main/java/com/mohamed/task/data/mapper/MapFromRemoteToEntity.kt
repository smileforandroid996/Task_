package com.mohamed.task.data.mapper


interface MapFromRemoteToEntity<R , E> {

    fun mapFromRemoteToEntity(model: R): E

}