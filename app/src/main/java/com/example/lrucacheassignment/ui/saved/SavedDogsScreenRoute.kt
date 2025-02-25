package com.example.lrucacheassignment.ui.saved

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SavedDogsScreenRoute(viewModel: SavedDogsViewModel, onBackPress: () -> Unit) {
    val uiState by viewModel.savedDogsUiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        viewModel.getSavedDogs()
    }
    SavedDogsScreen(
        uiState = uiState,
        onBackPress = onBackPress,
        onClearCacheClicked = { viewModel.clearCache() })
}