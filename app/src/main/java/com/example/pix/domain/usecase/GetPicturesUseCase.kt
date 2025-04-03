package com.example.pix.domain.usecase

import com.example.pix.domain.FlickrRepository
import com.example.pix.domain.entity.Picture
import javax.inject.Inject

class GetPicturesUseCase @Inject constructor(
    private val picturesRepository: FlickrRepository
) {
    suspend operator fun invoke(query: String): Result<List<Picture>> = picturesRepository.searchPictures(query)
}