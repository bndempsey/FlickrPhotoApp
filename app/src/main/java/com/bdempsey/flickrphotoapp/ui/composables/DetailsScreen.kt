package com.bdempsey.flickrphotoapp.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.bdempsey.flickrphotoapp.R

@Composable
fun DetailsScreen(
    url: String?,
    title: String?,
    id: String?
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ){ innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxWidth()) {
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Row {
                Text(stringResource(R.string.image_title))
                if (title != null) Text(title)

            }
            Row() {
                Text(stringResource(R.string.image_id))
                if (id != null) Text(id)
            }
        }
    }
}