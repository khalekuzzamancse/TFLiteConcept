package com.kzcse.tfliteconcept

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val md_theme_light_primary = Color(0xFF006688)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFC2E8FF)
val md_theme_light_onPrimaryContainer = Color(0xFF001E2B)
val md_theme_light_secondary = Color(0xFF9B4428)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFFFDBD0)
val md_theme_light_onSecondaryContainer = Color(0xFF3A0B00)
val md_theme_light_tertiary = Color(0xFF416916)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFC1F18F)
val md_theme_light_onTertiaryContainer = Color(0xFF0E2000)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFBFCFE)
val md_theme_light_onBackground = Color(0xFF191C1E)
val md_theme_light_surface = Color(0xFFFBFCFE)
val md_theme_light_onSurface = Color(0xFF191C1E)
val md_theme_light_surfaceVariant = Color(0xFFDCE3E9)
val md_theme_light_onSurfaceVariant = Color(0xFF40484D)
val md_theme_light_outline = Color(0xFF71787D)
val md_theme_light_inverseOnSurface = Color(0xFFF0F1F3)
val md_theme_light_inverseSurface = Color(0xFF2E3133)
val md_theme_light_inversePrimary = Color(0xFF75D1FF)
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFF006688)
val md_theme_light_outlineVariant = Color(0xFFC0C7CD)
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFF75D1FF)
val md_theme_dark_onPrimary = Color(0xFF003548)
val md_theme_dark_primaryContainer = Color(0xFF004D67)
val md_theme_dark_onPrimaryContainer = Color(0xFFC2E8FF)
val md_theme_dark_secondary = Color(0xFFFFB59E)
val md_theme_dark_onSecondary = Color(0xFF5E1800)
val md_theme_dark_secondaryContainer = Color(0xFF7C2D13)
val md_theme_dark_onSecondaryContainer = Color(0xFFFFDBD0)
val md_theme_dark_tertiary = Color(0xFFA6D476)
val md_theme_dark_onTertiary = Color(0xFF1C3700)
val md_theme_dark_tertiaryContainer = Color(0xFF2B5000)
val md_theme_dark_onTertiaryContainer = Color(0xFFC1F18F)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF191C1E)
val md_theme_dark_onBackground = Color(0xFFE1E2E5)
val md_theme_dark_surface = Color(0xFF191C1E)
val md_theme_dark_onSurface = Color(0xFFE1E2E5)
val md_theme_dark_surfaceVariant = Color(0xFF40484D)
val md_theme_dark_onSurfaceVariant = Color(0xFFC0C7CD)
val md_theme_dark_outline = Color(0xFF8A9297)
val md_theme_dark_inverseOnSurface = Color(0xFF191C1E)
val md_theme_dark_inverseSurface = Color(0xFFE1E2E5)
val md_theme_dark_inversePrimary = Color(0xFF006688)
val md_theme_dark_shadow = Color(0xFF000000)
val md_theme_dark_surfaceTint = Color(0xFF75D1FF)
val md_theme_dark_outlineVariant = Color(0xFF40484D)
val md_theme_dark_scrim = Color(0xFF000000)


val seed = Color(0xFF4FC3F7)


private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

@Composable
fun AppTheme(
  useDarkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable() () -> Unit
) {
  val colors = if (!useDarkTheme) {
    LightColors
  } else {
    DarkColors
  }

  MaterialTheme(
    colorScheme = colors,
    content = content
  )
}