package com.example.lrucacheassignment.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun Modifier.debounceClickable(
    debounceTime: Long = 500L, // 500ms debounce time
    onClick: () -> Unit
): Modifier {
    var isClickable by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(debounceTime)
        isClickable = true
    }
    return Modifier.clickable {
        if (isClickable) {
            isClickable = false
            onClick()

        }
    }
}
