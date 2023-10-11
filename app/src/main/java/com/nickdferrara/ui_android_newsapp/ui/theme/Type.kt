package com.nickdferrara.ui_android_newsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.nickdferrara.ui_android_newsapp.R

private val Chomsky = FontFamily(
    Font(R.font.chomsky_regular)
)

private val TimesNewRoman = FontFamily(
    Font(R.font.times_new_roman_regular),
    Font(R.font.times_new_roman_bold),
    Font(R.font.times_new_roman_italic),
    Font(R.font.times_new_roman_bold_italic)
)

@Suppress("DEPRECATION")
val defaultTextStyle = TextStyle(
    fontFamily = TimesNewRoman,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

val NewsifyTypography = Typography(
    titleLarge = defaultTextStyle.copy(
        fontSize = 30.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic
    ),
    bodyLarge = defaultTextStyle.copy(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        lineBreak = LineBreak.Paragraph
    ),
    displayLarge = defaultTextStyle.copy(
        fontFamily = Chomsky,
        fontSize = 32.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
        lineBreak = LineBreak.Paragraph
    ),
)