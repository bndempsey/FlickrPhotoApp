package com.bdempsey.flickrphotoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bdempsey.flickrphotoapp.ui.composables.SearchScreen
import com.bdempsey.flickrphotoapp.ui.theme.FlickrPhotoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlickrPhotoAppTheme {
                SearchScreen()
            }
        }
    }
}