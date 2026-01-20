package com.proto.mvi.common.widgets


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proto.mvi.common.widgets.utils.AsteriskPasswordVisualTransformation
import com.proto.mvi.ui.theme.Typography
import com.proto.mvi.ui.theme.labelStyle

// CustomText Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldView(
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = labelStyle.copy(
       fontSize = 14.sp,
    ),
    placeholder: @Composable (() -> Unit)? = null,
    label: String = "",
    labelTextStyle: TextStyle = Typography.labelSmall.copy(
        color = MaterialTheme.colorScheme.onPrimary, fontSize = 14.sp
    ),
    shape: RoundedCornerShape = RoundedCornerShape(48.dp),
    trailingIcon: @Composable (() -> Unit)? = null,
    isPasswordVisible: Boolean = true,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(48.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    leadingIcon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    prefix: @Composable (() -> Unit)? = null,
    errorText: String = "",
    errorTextStyle: TextStyle = Typography.labelSmall.copy(
        color = Color.White, fontSize = 12.sp
    ),
    errorTextModifier: Modifier = Modifier.padding(vertical = 4.dp, horizontal = 0.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent, // Make background transparent
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        focusedIndicatorColor = MaterialTheme.colorScheme.primary, // Underline color when focused
        unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant, // Underline color when unfocused
        disabledIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorIndicatorColor = MaterialTheme.colorScheme.error
    ),
    containerModifier: Modifier = Modifier.fillMaxWidth(),
) {

    val context = LocalContext.current
    val resources = context.resources
    val packageName = context.packageName
    val resourceId = resources.getIdentifier(errorText, "string", packageName)
    val errorLabel = if (resourceId != 0) {
        // Use the found integer ID to get the string
        resources.getString(resourceId)
    } else {
        // Return an empty string or a default value if not found
        ""
    }
    Column(
        modifier = containerModifier
    ) {
        if(label.isNotBlank()) {
            TextView(
                text = label,
                textStyle = labelTextStyle,
                modifier = Modifier.padding(bottom = 6.dp) // Ensure label fills the width of the Column
            )
        }
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            modifier = modifier,
            shape = shape,
            textStyle = textStyle,
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            leadingIcon = leadingIcon,
            readOnly = readOnly,
            singleLine = singleLine,
            prefix = prefix,
            colors = colors,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else AsteriskPasswordVisualTransformation(),
        )
        if (errorLabel.isNotBlank()) {
            TextView(
                text = errorLabel,
                textStyle = errorTextStyle,
                modifier = errorTextModifier // Ensure label fills the width of the Column
            )
        }
    }
}