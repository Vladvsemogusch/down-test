package cc.anisimov.vladyslav.downtest.ui.screen.snap_match.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cc.anisimov.vladyslav.downtest.R
import cc.anisimov.vladyslav.downtest.domain.model.User
import cc.anisimov.vladyslav.downtest.ui.base.component.UrlImage
import cc.anisimov.vladyslav.downtest.ui.screen.snap_match.state.SnapMatchScreenUiState
import cc.anisimov.vladyslav.downtest.ui.theme.DownTestTheme
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.android.gms.maps.model.LatLng

@Composable
fun AvatarWithRipple(
    modifier: Modifier = Modifier,
    uiState: SnapMatchScreenUiState
) {
    val imageUrl = when (uiState) {
        is SnapMatchScreenUiState.ReadyToMatch -> uiState.userImageUrl
        is SnapMatchScreenUiState.MatchingPhaseOne -> uiState.userImageUrl
        is SnapMatchScreenUiState.MatchingPhaseTwo -> uiState.userImageUrl
        is SnapMatchScreenUiState.MatchResult -> uiState.matchedUser.imageUrl
    }

    Box(
        modifier = modifier
    ) {
        val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_ripple))
        LottieAnimation(
            composition = lottieComposition,
            iterations = Int.MAX_VALUE,
            contentScale = ContentScale.Fit,
        )
        UrlImage(
            modifier = Modifier
                .size(92.dp)
                .align(Alignment.Center),
            url = imageUrl,
            shape = CircleShape
        )
        if (uiState is SnapMatchScreenUiState.MatchResult) {
            Card(
                modifier = Modifier
                    .padding(top = 114.dp)
                    .height(52.dp)
                    .align(Alignment.Center),
                shape = MaterialTheme.shapes.medium
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = uiState.matchedUser.name,
                            style = MaterialTheme.typography.h4
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier
                                    .height(7.dp)
                                    .padding(end = 4.dp),
                                painter = painterResource(id = R.drawable.ic_location),
                                contentDescription = null,
                                tint = MaterialTheme.colors.onPrimary
                            )
                            Text(
                                text = uiState.matchedUser.locationName,
                                style = MaterialTheme.typography.h6
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AvatarWithRipplePreview() {
    DownTestTheme {
        AvatarWithRipple(
            uiState = SnapMatchScreenUiState.MatchResult(
                0, User(
                    name = "Test",
                    imageUrl = "",
                    locationName = "TestL",
                    location = LatLng(47.039027, 66.457992)
                )
            )
        )
    }
}