package com.bdempsey.flickrphotoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bdempsey.flickrphotoapp.ui.composables.DetailsScreen
import com.bdempsey.flickrphotoapp.ui.composables.SearchScreen
import com.bdempsey.flickrphotoapp.ui.theme.FlickrPhotoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            FlickrPhotoAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = "searchImages"
                ) {
                    composable("searchImages") { SearchScreen(navController = navController) }
                    composable("imageDetails/{title}/{id}/{url}") { backStackEntry ->
                        val title = backStackEntry.arguments?.getString("title")
                        val id = backStackEntry.arguments?.getString("id")
                        val url = backStackEntry.arguments?.getString("url")
                        DetailsScreen(url, title, id)
                    }
                }
//                SearchScreen()
            }
        }
    }
}