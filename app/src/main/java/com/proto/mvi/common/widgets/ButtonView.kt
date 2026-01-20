package com.proto.mvi.common.widgets

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.proto.mvi.ui.theme.Typography

@Composable
fun ButtonView(
    buttonModifier: Modifier = Modifier,
    buttonEnabled: Boolean = true,
    onClick: () -> Unit,
    label: String,
    labelStyle: TextStyle = Typography.labelSmall,
    labelModifier: Modifier = Modifier, // Default to Modifier
    labelMaxLines: Int = Int.MAX_VALUE,
    labelOverflow: TextOverflow = TextOverflow.Visible
) {
    Button(
        onClick = onClick,
        enabled = buttonEnabled,
        modifier =buttonModifier
    ) {
        TextView(
            text = label,
            textStyle = labelStyle,
            modifier = labelModifier,
            maxLines = labelMaxLines,
            overflow = labelOverflow
        )
    }
}