package cc.anisimov.vladyslav.downtest.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import cc.anisimov.vladyslav.downtest.ui.theme.extra.ExtraColors
import cc.anisimov.vladyslav.downtest.ui.theme.extra.LocalExtraColors

private val LightColorPalette = lightColors(
    primary = Color.Black,
    primaryVariant = Grey,
    secondary = Pink,
    background = Color.Black,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
)

private val ExtraColorsPalette = ExtraColors(indicatorGreen = Green, unselectedGrey = MidGrey)

@Composable
fun DownTestTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalExtraColors provides ExtraColorsPalette) {
        MaterialTheme(
            colors = LightColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}



inline val Colors.indicatorGreen: Color
    @ReadOnlyComposable
    @Composable
    get() = LocalExtraColors.current.indicatorGreen

inline val Colors.unselectedGrey: Color
    @ReadOnlyComposable
    @Composable
    get() = LocalExtraColors.current.unselectedGrey