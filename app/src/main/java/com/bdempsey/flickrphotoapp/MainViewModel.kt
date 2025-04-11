package com.bdempsey.flickrphotoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bdempsey.flickrphotoapp.data.FlickrImage
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _flickrImages = MutableLiveData<List<FlickrImage>>()
    val flickerImages: LiveData<List<FlickrImage>> = _flickrImages

    init {
        searchImages("")
    }

    fun searchImages(search: String) {
            viewModelScope.launch {
                val searchResponse =
                    if (search.isEmpty()) FlickrApi.apiService.fetchRecentImages()
                    else FlickrApi.apiService.fetchImages(search)
                val imagesList = searchResponse.photos.photo.map { image ->
                    FlickrImage(
                        url="https://live.staticflickr.com/${image.server}/${image.id}_${image.secret}.jpg"
                    )
                }
                _flickrImages.postValue(imagesList)
            }

    }
}