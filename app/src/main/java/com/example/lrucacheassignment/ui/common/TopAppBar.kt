@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lrucacheassignment.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lrucacheassignment.R

@Composable
fun DogTopAppBar(onBackPressed: () -> Unit, title: String) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                Row(modifier = Modifier.debounceClickable {
                    onBackPressed()
                },verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        tint = colorResource(R.color.color_4286F4),
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "back_icon"
                    )
                    Text(
                        text = "Back",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = colorResource(R.color.color_4286F4),
                    )
                }
            }
        )
        HorizontalDivider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.5f))
    }
}