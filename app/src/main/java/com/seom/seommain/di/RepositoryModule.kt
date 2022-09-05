package com.seom.seommain.di

import com.seom.seommain.data.repository.MailRepository
import com.seom.seommain.data.repository.impl.MailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMailRepository(
        mailRepositoryImpl: MailRepositoryImpl
    ): MailRepository
}