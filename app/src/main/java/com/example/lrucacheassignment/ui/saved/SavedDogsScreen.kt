@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.lrucacheassignment.ui.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.lrucacheassignment.ui.common.DogTopAppBar
import com.example.lrucacheassignment.ui.common.RippleButton

@Composable
fun SavedDogsScreen(
    uiState: SavedDogsViewModel.SavedDogsUiState,
    onClearCacheClicked: () -> Unit,
    onBackPress: () -> Unit
) {
    val pagerState = rememberPagerState { uiState.savedDogs?.size ?: 0 }
    Scaffold(topBar = {
        DogTopAppBar(
            onBackPressed = { onBackPress() },
            title = "My Recently Generated Dogs!"
        )
    }) {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else if (uiState.savedDogs?.isNotEmpty() == true) {
                uiState.savedDogs.let { savedDogs ->
                    HorizontalPager(
                        modifier = Modifier.fillMaxWidth(),
                        state = pagerState
                    ) { page ->
                        GlideImage(
                            modifier = Modifier
                                .height(400.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Fit,
                            model = savedDogs.getOrNull(page),
                            contentDescription = "dog_image"
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    RippleButton(text = "Clear Dogs!") {
                        onClearCacheClicked()
                    }
                }
            } else {
                Text(
                    textAlign = TextAlign.Center,
                    text = "No Dogs Found!",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun SavedDogsScreenPreview() {
    SavedDogsScreen(uiState = SavedDogsViewModel.SavedDogsUiState(), onClearCacheClicked = {}, onBackPress = {})
}