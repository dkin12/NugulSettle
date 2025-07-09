package com.nugul.settle.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nugul.settle.R

// 폰트 정의
val Suit = FontFamily(
    Font(R.font.suit_bold, FontWeight.Bold),
    Font(R.font.suit_seminbold, FontWeight.SemiBold),
    Font(R.font.suit_medium, FontWeight.Medium),
    Font(R.font.suit_regular, FontWeight.Normal),
)

var Paperlogy = FontFamily(
    Font(R.font.paperlogy_regular, FontWeight.Normal)
)

var TitleFont = FontFamily(
    Font(R.font.title,FontWeight.Normal)
)

// Typography 설정
val AppTypography = Typography(
    displayLarge = TextStyle( // font_title
        fontFamily = TitleFont,
        fontSize = 32.sp
    ),
    titleLarge = TextStyle( // font_headline1
        fontFamily = Suit,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle( // font_headline2
        fontFamily = Suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle( // font_subheadline
        fontFamily = Suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    bodyLarge = TextStyle( // font_body1
        fontFamily = Suit,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle( // font_body2
        fontFamily = Suit,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle( // font_description
        fontFamily = Suit,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle( // font_subtitle
        fontFamily = Paperlogy,
        fontSize = 16.sp
    )
)