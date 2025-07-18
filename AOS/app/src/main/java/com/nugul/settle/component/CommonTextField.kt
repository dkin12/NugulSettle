package com.nugul.settle.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import java.text.DecimalFormat

@Composable
fun CommonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String = "입력하세요",
    supportingText: String = "",
    maxLength: Int = Int.MAX_VALUE,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier,
    singleLine : Boolean = true,
    showClearIcon: Boolean = true,
) {
    TextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.length <= maxLength) {
                onValueChange(newValue)
            }
        },
        placeholder = {
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = placeholderText
            ) },
        trailingIcon = {
            if (showClearIcon && value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(Icons.Default.Close, contentDescription = "Clear text")
                }
            }
        },
        supportingText = {
            if (supportingText.isNotEmpty()) {
                Text(text = supportingText, style = MaterialTheme.typography.bodySmall)
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        ),
        singleLine = singleLine,
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

fun formatPriceWithComma(input: String): String {
    val numeric = input.toLongOrNull() ?: 0L
    return DecimalFormat("#,###").format(numeric.coerceAtMost(5_000_000L))
}