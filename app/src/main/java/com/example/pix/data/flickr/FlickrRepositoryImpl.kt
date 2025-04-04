package com.example.pix.data.flickr

import com.example.pix.data.flickr.mapper.QUALITY_MEDIUM
import com.example.pix.data.flickr.mapper.toEntity
import com.example.pix.domain.FlickrRepository
import com.example.pix.domain.entity.Picture

class FlickrRepositoryImpl(
    private val flickrApi: FlickrApi,
) : FlickrRepository {

    override suspend fun searchPictures(query: String): Result<List<Picture>> = search(query)

    private suspend fun search(
        text: String,
        page: Int = 1,
        count: Int = 100
    ): Result<List<Picture>> = runCatching {
        val result = flickrApi.search(text, page, count)
        result.photos?.let { photos ->
            photos.photo.map { it.toEntity(QUALITY_MEDIUM) }
        } ?: throw IllegalStateException("No photos found")
    }
}