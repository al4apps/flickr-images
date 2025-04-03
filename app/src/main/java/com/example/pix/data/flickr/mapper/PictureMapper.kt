package com.example.pix.data.flickr.mapper

import com.example.pix.data.flickr.dto.PhotoDto
import com.example.pix.domain.entity.Picture

// https://www.flickr.com/services/api/misc.urls.html
fun PhotoDto.toEntity(quality: String): Picture = Picture(
    title = title,
    url = "https://live.staticflickr.com/${server}/${id}_${secret}_${quality}.jpg",
    urlMax = "https://live.staticflickr.com/${server}/${id}_${secret}_${QUALITY_LARGE}.jpg",
)

const val QUALITY_SMALL = "n"
const val QUALITY_MEDIUM = "z"
const val QUALITY_LARGE = "b"

