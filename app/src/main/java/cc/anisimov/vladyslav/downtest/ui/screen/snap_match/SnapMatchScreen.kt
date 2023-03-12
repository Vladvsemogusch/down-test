package cc.anisimov.vladyslav.downtest.ui.screen.snap_match

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import cc.anisimov.vladyslav.downtest.R
import cc.anisimov.vladyslav.downtest.ui.base.component.rememberMapViewWithLifecycle
import cc.anisimov.vladyslav.downtest.ui.screen.snap_match.component.*
import cc.anisimov.vladyslav.downtest.ui.screen.snap_match.state.SnapMatchScreenUiState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SnapMatchRoute(
    viewModel: SnapMatchViewModel
) {
    val uiState by viewModel.uiStateFlow.collectAsState()

    SnapMatchScreen(
        uiState = uiState,
        viewModel,
        viewModel.mapPositionFlow
    )
}

//  1. Poppins font is intrinsically not vertically aligned.
//  Additional padding was required in some cases
//  2. To avoid map resizing and flickering on state change,
//  we need to keep the map the same size at all times
@Composable
fun SnapMatchScreen(
    uiState: SnapMatchScreenUiState,
    actions: SnapMatchActions,
    cameraPositionFlow: StateFlow<LatLng>
) {
    val mapView = rememberMapViewWithLifecycle()
    LaunchedEffect(Unit) {
        cameraPositionFlow.collect {
            moveMap(mapView = mapView, newLocation = it)
        }
    }
    val spinCounterTopPadding = remember(uiState) {
        when (uiState) {
            is SnapMatchScreenUiState.ReadyToMatch -> 80.dp
            else -> 28.dp
        }
    }
    val mapBottomPadding = remember(uiState) {
        when (uiState) {
            is SnapMatchScreenUiState.ReadyToMatch -> 70.dp
            else -> 132.dp
        }
    }

    Surface {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp)
            ) {
                //  Snap Match label
                if (uiState is SnapMatchScreenUiState.ReadyToMatch) {
                    Box(
                        modifier = Modifier
                            .height(68.dp)
                            .fillMaxWidth()
                            .zIndex(1f)
                            .background(color = MaterialTheme.colors.background)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .align(Alignment.BottomCenter),
                            text = stringResource(id = R.string.snap_match_title),
                            style = MaterialTheme.typography.h1
                        )
                    }

                }
                MapComponent(
                    modifier = Modifier
                        .padding(bottom = mapBottomPadding),
                    mapView = mapView
                )
                if (uiState !is SnapMatchScreenUiState.ReadyToMatch)
                    StopButton(
                        modifier = Modifier
                            .padding(start = 24.dp, top = 28.dp)
                            .height(42.dp),
                        onClick = actions::stopMatching
                    )
                SpinCounter(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = spinCounterTopPadding, end = 24.dp),
                    spinCount = uiState.spinCount
                )
                //  fillMaxWidth because of enormous padding in lottie animation,
                //  if we want to avoid scaling view
                //  TODO ask designer to optimize animation file
                AvatarWithRipple(
                    modifier = Modifier
                        .padding(top = 156.dp)
                        .fillMaxWidth()
                        .align(Alignment.TopCenter),
                    uiState = uiState
                )
                if (uiState is SnapMatchScreenUiState.ReadyToMatch) {
                    OnlineCounter(
                        modifier = Modifier
                            .padding(bottom = 174.dp)
                            .align(Alignment.BottomCenter),
                        usersOnlineCount = uiState.onlineUserCount
                    )
                }
                BottomSheetContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = MaterialTheme.shapes.large
                        )
                        .align(Alignment.BottomCenter)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = actions::startMatching
                        ),
                    uiState = uiState
                )
            }
            if (uiState !is SnapMatchScreenUiState.ReadyToMatch) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.secondary,
                    backgroundColor = MaterialTheme.colors.background
                )
            }
            if (uiState is SnapMatchScreenUiState.ReadyToMatch) {
                DownBottomNavigation(
                    modifier = Modifier
                        .height(66.dp)
                        .fillMaxWidth()
                        .background(Color.Magenta)
                )
            }
        }
    }
}

fun moveMap(mapView: MapView, newLocation: LatLng) {
    mapView.getMapAsync {
        val cameraUpdate = CameraUpdateFactory.newLatLng(newLocation)
        it.animateCamera(cameraUpdate)
    }
}