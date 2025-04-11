package com.bdempsey.flickrphotoapp

import com.bdempsey.flickrphotoapp.data.ImageSearchResponse
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import util.Constants.Companion.FLICKR_API_KEY
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.flickr.com/services/rest/"
private const val CONNECTION_TIMEOUT_MS: Long = 10

object FlickrApi {
    val apiService: ApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                )
            )
            .client(
                OkHttpClient.Builder().connectTimeout(
                    CONNECTION_TIMEOUT_MS,
                    TimeUnit.SECONDS
                ).addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("?method=flickr.photos.search&api_key=$FLICKR_API_KEY&text={searchString}&format=json&nojsoncallback=1")
    suspend fun fetchImages(@Path("searchString") searchString: String): ImageSearchResponse

    @GET("?method=flickr.photos.getRecent&api_key=$FLICKR_API_KEY&format=json&nojsoncallback=1")
    suspend fun fetchRecentImages(): ImageSearchResponse
}