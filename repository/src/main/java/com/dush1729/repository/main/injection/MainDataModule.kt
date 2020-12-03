package com.dush1729.repository.main.injection

import com.apollographql.apollo.ApolloClient
import com.dush1729.repository.main.IMainRepository
import com.dush1729.repository.main.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainDataModule {
    @Provides
    @Singleton
    fun provideMainRepository(apolloClient: ApolloClient): IMainRepository {
        return MainRepository(apolloClient)
    }
}