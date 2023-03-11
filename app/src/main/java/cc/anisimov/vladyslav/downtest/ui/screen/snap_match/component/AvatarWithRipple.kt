package cc.anisimov.vladyslav.downtest.ui.screen.snap_match.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cc.anisimov.vladyslav.downtest.R
import cc.anisimov.vladyslav.downtest.ui.base.component.UrlImage
import cc.anisimov.vladyslav.downtest.ui.theme.DownTestTheme
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun AvatarWithRipple(modifier: Modifier = Modifier, userImageUrl: String) {
    Box(
        modifier = modifier
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_ripple))
        LottieAnimation(
            composition = composition,
            iterations = Int.MAX_VALUE,
            contentScale = ContentScale.Fit,
        )
        UrlImage(
            modifier = Modifier.size(92.dp).align(Alignment.Center),
            url = userImageUrl,
            shape = CircleShape
        )
    }
}

@Preview
@Composable
fun AvatarWithRipplePreview() {
    DownTestTheme {
        AvatarWithRipple(userImageUrl = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")
    }
}