package com.mohamed.task.ui.di

import com.mohamed.task.data.network.ServiceApi
import com.mohamed.task.data.repository.UsersRepositoryImpl
import com.mohamed.task.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
abstract class UsersModule {
    companion object {
        @Provides
        fun provideServiceApi(@Named("user") retrofit: Retrofit) = retrofit.create(ServiceApi::class.java)
    }
    @Binds
    internal abstract fun bindsUserRepository(usersRepositoryImpl: UsersRepositoryImpl): UserRepository
}