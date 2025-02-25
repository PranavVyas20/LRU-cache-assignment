package com.example.lrucacheassignment.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lrucacheassignment.R
import com.example.lrucacheassignment.ui.common.RippleButton

@Composable
fun DogGeneratorHomeScreen(
    onGenerateDogClick: () -> Unit,
    onViewSavedDogsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Random Dog Generator!")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RippleButton(text = "Generate Dogs!") {
                onGenerateDogClick()
            }
            RippleButton(text = "My Recently Generated Dogs!") {
                onViewSavedDogsClick()
            }
        }
    }
}

@Preview
@Composable
fun DogGeneratorHomeScreenPreview() {
    DogGeneratorHomeScreen(onGenerateDogClick = {}, onViewSavedDogsClick = {})
}