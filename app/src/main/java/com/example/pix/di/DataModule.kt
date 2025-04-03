package com.example.pix.di

import com.example.pix.data.flickr.FlickrApi
import com.example.pix.data.flickr.FlickrRepositoryImpl
import com.example.pix.domain.FlickrRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideFlickrRepository(flickrApi: FlickrApi): FlickrRepository {
        return FlickrRepositoryImpl(flickrApi)
    }
}