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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError = _isError

    init {
        searchImages("")
    }

    fun searchImages(search: String) {
            viewModelScope.launch {
                _isLoading.postValue(true)
                _isError.postValue(false)
                try {
                    val searchResponse =
                        if (search.isEmpty()) FlickrApi.apiService.fetchRecentImages()
                        else FlickrApi.apiService.fetchImages(search)
                    val imagesList = searchResponse.photos.photo.map { image ->
                        FlickrImage(
                            id = image.id,
                            url = "https://live.staticflickr.com/${image.server}/${image.id}_${image.secret}.jpg",
                            title = image.title
                        )
                    }
                    _isLoading.postValue(false)
                    _flickrImages.postValue(imagesList)
                }catch(exception: Exception) {
                    // TODO: Implement better error handling
                    _isLoading.postValue(false)
                    _isError.postValue(true)
                }
            }

    }
}