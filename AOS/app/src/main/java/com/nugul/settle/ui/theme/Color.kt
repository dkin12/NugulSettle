package com.nugul.settle.ui.theme

import androidx.compose.ui.graphics.Color


// Light Theme
val PrimaryLight = Color(0xFF95796C)
val SecondaryLight = Color(0xFFBBA9A1)
val TertiaryLight = Color(0xFFEAD6C6)
val BackgroundLight = Color(0xFFFAFAFA)
val SurfaceLight = Color(0xFFFFFFFF)
val OnPrimaryLight = Color(0xFF333333)
val OnBackgroundLight = Color(0xFF333333)

// Dark Theme
val PrimaryDark = Color(0xFF7A6055)
val SecondaryDark = Color(0xFF9C8379)
val TertiaryDark = Color(0xFF5A3E36)
val BackgroundDark = Color(0xFF1C1A18)
val SurfaceDark = Color(0xFF333333)
val OnPrimaryDark = Color(0xFFFAFAFA)
val OnBackgroundDark = Color(0xFFEFE5DE)

// ColorPalette
data class GroupColor(val light: Color, val base: Color)
val userColors = mapOf(
    "Red" to GroupColor(Color(0xFFFFE9EB), Color(0xFFF1516A)),
    "Orange" to GroupColor(Color(0xFFFCE3D5), Color(0xFFEEA573)),
    "Yellow" to GroupColor(Color(0xFFFFFBC2), Color(0xFFF2E93D)),
    "Green" to GroupColor(Color(0xFFE6FAE3), Color(0xFF08C686)),
    "Blue" to GroupColor(Color(0xFFE1E7F3), Color(0xFF6190FC)),
    "Purple" to GroupColor(Color(0xFFF5EAF7), Color(0xFFEA7AFF)),
    "Pink" to GroupColor(Color(0xFFFFE6F3), Color(0xFFFF8BC7)),

)


