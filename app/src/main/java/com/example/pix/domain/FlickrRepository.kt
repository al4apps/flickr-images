package com.example.pix.domain

import com.example.pix.domain.entity.Picture

interface FlickrRepository {

    suspend fun searchPictures(query: String): Result<List<Picture>>
}