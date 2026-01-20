package com.proto.mvi.common.widgets

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun TextView(
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall,
    modifier: Modifier = Modifier, // Default to Modifier
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Visible
) {
    // Apply margin first, then padding, then pass to Text
    Text(
        text = text,
        style = textStyle,
        modifier = modifier,// Use provided color or style's color
        maxLines = maxLines,
        overflow = overflow
    )
}