package com.example.lrucacheassignment.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lrucacheassignment.R

@Composable
fun RippleButton(text: String, onClick: () -> Unit) {
    val defaultButtonColor = colorResource(R.color.color_4286F4)
    var textColor by remember { mutableStateOf(Color.White) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> textColor = defaultButtonColor
                is PressInteraction.Release, is PressInteraction.Cancel -> textColor = Color.White
            }
        }
    }

    Box(modifier = Modifier
        .clip(RoundedCornerShape(18.dp))
        .background(defaultButtonColor)
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            onClick()
        }
        .padding(horizontal = 12.dp, vertical = 4.dp)) {
        Text(
            text = text,
            color = textColor
        )
    }
}

@Composable
@Preview
fun RippleButtonPreview() {
    RippleButton("Hello", onClick = {})
}
