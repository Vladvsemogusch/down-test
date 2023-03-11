package cc.anisimov.vladyslav.downtest.ui.screen.snap_match

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import cc.anisimov.vladyslav.downtest.R
import cc.anisimov.vladyslav.downtest.ui.screen.snap_match.component.rememberMapViewWithLifecycle
import cc.anisimov.vladyslav.downtest.ui.theme.DownTestTheme
import cc.anisimov.vladyslav.downtest.ui.theme.indicatorGreen
import cc.anisimov.vladyslav.downtest.ui.theme.unselectedGrey
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.android.gms.maps.model.MapStyleOptions

@Composable
fun SnapMatchRoute(
    viewModel: SnapMatchViewModel
) {
//    val uiState by viewModel.uiState.collectAsState()

    SnapMatchScreen(
//        uiState = uiState,
    )
}

@Composable
fun SnapMatchScreen() {
    Surface {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 30.dp),
                text = stringResource(id = R.string.snap_match_title),
                style = MaterialTheme.typography.h1
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp)
            ) {
                Map(
                    modifier = Modifier
                        .padding(bottom = 130.dp)
                )
                SpinCounter(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 24.dp),
                    spinCount = 4
                )
                //  fillMaxWidth because of enormous padding in lottie animation, if we want to avoid scaling view
                //  TODO ask designer to optimize animation file
                AvatarWithRipple(
                    modifier = Modifier
                        .padding(top = 87.dp)
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                )
                OnlineCounter(
                    modifier = Modifier
                        .padding(bottom = 174.dp)
                        .align(Alignment.BottomCenter),
                    onlineCount = 4
                )
                BottomSheetContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = MaterialTheme.shapes.large
                        )
                        .align(Alignment.BottomCenter),
                )


            }
            BottomNavigation(
                modifier = Modifier
                    .height(66.dp)
                    .fillMaxWidth()
                    .background(Color.Magenta)
            ) {
                BottomNavigationItem(
                    selected = true,
                    onClick = { /*TODO*/ },
                    icon = {
                        //  Provided icon doesn't tint correctly, not using tint until fixed
                        Image(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_snapmatch),
                            contentDescription = null,
                        )
                    })
                BottomNavigationItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    selectedContentColor = MaterialTheme.colors.onPrimary,
                    unselectedContentColor = MaterialTheme.colors.unselectedGrey,
                    icon = {
                        // Icon wasn't provided had to use custom
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = R.drawable.ic_heart),
                            contentDescription = null,
                        )
                    })
            }
        }
    }
}

@Composable
fun Map(modifier: Modifier = Modifier) {
    val mapView = rememberMapViewWithLifecycle()
    AndroidView(modifier = modifier, factory = {
        mapView.getMapAsync { googleMap ->
            val mapStyle = MapStyleOptions.loadRawResourceStyle(
                it, R.raw.map_style
            )
            googleMap.setMapStyle(mapStyle)
        }
        mapView
    })
}

@Composable
fun SpinCounter(modifier: Modifier = Modifier, spinCount: Int) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Row(
            modifier = Modifier.size(width = 69.dp, height = 42.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .size(16.dp),
                painter = painterResource(id = R.drawable.ic_snapmatch),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 9.dp, top = 2.dp),
                text = spinCount.toString(),
                style = MaterialTheme.typography.h3,
            )
        }
    }
}

@Composable
fun AvatarWithRipple(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_ripple))
        LottieAnimation(
            composition = composition,
            iterations = Int.MAX_VALUE,
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
fun OnlineIndicator(modifier: Modifier = Modifier) {
    val indicatorColor = MaterialTheme.colors.indicatorGreen
    Canvas(modifier = modifier, onDraw = {
        drawCircle(color = indicatorColor)
    })
}

@Composable
fun OnlineCounter(modifier: Modifier = Modifier, onlineCount: Int) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Row(
            modifier = Modifier
                .height(24.dp)
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OnlineIndicator(modifier = Modifier.size(6.dp))
            Text(
                modifier = Modifier.padding(start = 5.dp, top = 2.dp),
                text = stringResource(R.string.snap_match_online_counter, onlineCount),
                style = MaterialTheme.typography.h5,
            )
        }
    }
}

@Composable
fun BottomSheetContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_tap_to_match))
        //  Scale because of enormous intrinsic padding in lottie animation
        //  TODO ask designer to optimize animation file
        LottieAnimation(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(60.dp)
                .scale(2.5f),
            composition = composition,
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

@Preview
@Composable
fun SpinCounterPreview() {
    DownTestTheme {
        SpinCounter(spinCount = 4)
    }
}
