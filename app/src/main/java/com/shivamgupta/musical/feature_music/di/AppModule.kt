package com.shivamgupta.musical.feature_music.di

import com.shivamgupta.musical.feature_music.data.local.MusicLocalDataSource
import com.shivamgupta.musical.feature_music.data.repository.MusicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMusicLocalDataSource(): MusicLocalDataSource{
        TODO("Setup local data source")
    }

    @Provides
    @Singleton
    fun provideMusicRepository(musicLocalDataSource: MusicLocalDataSource): MusicRepository{
        TODO("create and return repository instance")
    }

}