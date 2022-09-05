package com.dabenxiang.jys.basechatroom.di

import com.dabenxiang.jys.basechatroom.model.manager.RepositoryManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object managerModule {

    @Singleton
    @Provides
    fun provideRepositoryManager(
            @ApiModule.GraphqlOkHttpClient graphqlOkHttpClient: OkHttpClient,
    ): RepositoryManager {
        return RepositoryManager(graphqlOkHttpClient)
    }
}
