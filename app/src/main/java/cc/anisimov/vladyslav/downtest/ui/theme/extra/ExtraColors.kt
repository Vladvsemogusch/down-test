package cc.anisimov.vladyslav.downtest.ui.theme.extra

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtraColors(
    val indicatorGreen: Color,
    val unselectedGrey: Color
)

val LocalExtraColors = staticCompositionLocalOf<ExtraColors> { error("No extra colors provided") }