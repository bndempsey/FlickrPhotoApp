package com.bdempsey.flickrphotoapp.data

data class ImageSearchResponse(val photos: ImagesMetaData)
data class ImagesMetaData(
    val page: Int,
    val photo: List<ImageResponse>
)
data class ImageResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)