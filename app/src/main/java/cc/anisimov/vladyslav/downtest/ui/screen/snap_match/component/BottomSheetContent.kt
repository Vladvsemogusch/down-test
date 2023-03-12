package cc.anisimov.vladyslav.downtest.ui.screen.snap_match.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cc.anisimov.vladyslav.downtest.R
import cc.anisimov.vladyslav.downtest.ui.screen.snap_match.state.SnapMatchScreenUiState
import cc.anisimov.vladyslav.downtest.ui.theme.DownTestTheme
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun BottomSheetContent(modifier: Modifier = Modifier, uiState: SnapMatchScreenUiState) {
    when (uiState) {
        is SnapMatchScreenUiState.ReadyToMatch -> VariantReadyToMatch(modifier = modifier)
        is SnapMatchScreenUiState.MatchingPhaseOne -> VariantMatchingPhaseOne(modifier = modifier)
        is SnapMatchScreenUiState.MatchingPhaseTwo -> VariantMatchingPhaseTwo(
            modifier = modifier,
            numberInLine = uiState.numberInQueue
        )
        is SnapMatchScreenUiState.MatchResult -> VariantMatchResult(modifier = modifier)
    }
}

@Composable
private fun VariantReadyToMatch(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_tap_to_match))
        //  Scale because of enormous intrinsic padding in lottie animation
        //  TODO ask designer to optimize animation file
        LottieAnimation(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(60.dp)
                .scale(2.5f),
            composition = lottieComposition,
            iterations = Int.MAX_VALUE,
            contentScale = ContentScale.Fit,
        )
        Text(
            modifier = Modifier.padding(top = 13.dp),
            text = stringResource(id = R.string.snap_match_tap_to_match),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.secondary
        )
        Text(
            text = stringResource(id = R.string.snap_match_start_chatting),
            style = MaterialTheme.typography.h4,
        )
    }
}

// Location lottie animation wasn't provided
@Composable
private fun VariantMatchingPhaseOne(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(60.dp),
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 13.dp),
            text = stringResource(id = R.string.snap_match_hang_tight),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.secondary
        )
        Text(
            text = stringResource(id = R.string.snap_match_matching_you),
            style = MaterialTheme.typography.h4,
        )
    }
}

@Composable
private fun VariantMatchingPhaseTwo(modifier: Modifier = Modifier, numberInLine: Int) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(60.dp),
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 13.dp),
            text = stringResource(id = R.string.snap_match_number_in_line, numberInLine),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.secondary
        )
        Text(
            text = stringResource(id = R.string.snap_match_all_good),
            style = MaterialTheme.typography.h4,
        )
    }
}

@Composable
private fun VariantMatchResult(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(60.dp),
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 13.dp),
            text = stringResource(id = R.string.snap_match_yay),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.secondary
        )
        Text(
            text = stringResource(id = R.string.snap_match_heres_your),
            style = MaterialTheme.typography.h4,
        )
    }
}

@Preview
@Composable
fun BottomSheetContentPreview() {
    DownTestTheme {
        BottomSheetContent(
            uiState = SnapMatchScreenUiState.ReadyToMatch(
                spinCount = 0,
                onlineUserCount = 0,
                userImageUrl = ""
            )
        )
    }
}