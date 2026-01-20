package com.proto.mvi.common.widgets.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

// Custom visual transformation to show '*' instead of default '•'
class AsteriskPasswordVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformed = AnnotatedString("*".repeat(text.text.length))
        return TransformedText(transformed, OffsetMapping.Companion.Identity)
    }
}