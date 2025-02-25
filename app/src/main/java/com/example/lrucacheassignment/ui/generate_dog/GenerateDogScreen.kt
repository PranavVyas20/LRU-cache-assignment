@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.lrucacheassignment.ui.generate_dog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.lrucacheassignment.ui.common.DogTopAppBar
import com.example.lrucacheassignment.ui.common.RippleButton

@Composable
fun GenerateDogScreen(
    generateDogUiState: GenerateDogViewModel.GenerateDogUiState,
    onGenerateDogClick: () -> Unit,
    onBackPress: () -> Unit
) {
    Scaffold(
        topBar = {
            DogTopAppBar(
                title = "Generate Dogs!",
                onBackPressed = { onBackPress() })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            if (generateDogUiState.isLoading) {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            } else {
                GlideImage(
                    modifier = Modifier.size(200.dp),
                    model = generateDogUiState.imageUrl.orEmpty(),
                    contentDescription = "dog_image"
                )
            }
            RippleButton(text = "Generate Dogs!") {
                onGenerateDogClick()
            }
        }
    }


}

@Preview
@Composable
fun GenerateDogScreenPreview() {
    GenerateDogScreen(
        onBackPress = {},
        generateDogUiState = GenerateDogViewModel.GenerateDogUiState(),
        onGenerateDogClick = {}
    )
}