package com.example.lrucacheassignment.ui.generate_dog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun GeneratedDogScreenRoute(
    viewModel: GenerateDogViewModel,
    onBackPress: () -> Unit
) {
    val uiState by viewModel.generateDogUiState.collectAsStateWithLifecycle()
    GenerateDogScreen(
        generateDogUiState = uiState,
        onGenerateDogClick = viewModel::getRandomDogImage,
        onBackPress = onBackPress
    )

}