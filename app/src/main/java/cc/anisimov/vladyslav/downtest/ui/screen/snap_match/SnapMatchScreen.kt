package cc.anisimov.vladyslav.downtest.ui.screen.snap_match

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cc.anisimov.vladyslav.downtest.R
import cc.anisimov.vladyslav.downtest.ui.screen.snap_match.component.*
import cc.anisimov.vladyslav.downtest.ui.screen.snap_match.state.SnapMatchScreenUiState
import cc.anisimov.vladyslav.downtest.ui.theme.unselectedGrey

@Composable
fun SnapMatchRoute(
    viewModel: SnapMatchViewModel
) {
    val uiState by viewModel.uiStateFlow.collectAsState()

    SnapMatchScreen(
        uiState = uiState,
    )
}

//  Poppins font is intrinsically not vertically aligned.
//  That's why additional padding was required in some cases
@Composable
fun SnapMatchScreen(uiState: SnapMatchScreenUiState) {
    val userImageUrl = when (uiState) {
        is SnapMatchScreenUiState.ReadyToMatch -> uiState.userImageUrl
    }

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
                MapComponent(
                    modifier = Modifier
                        .padding(bottom = 130.dp)
                )
                SpinCounter(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 24.dp),
                    spinCount = uiState.spinCount
                )
                //  fillMaxWidth because of enormous padding in lottie animation,
                //  if we want to avoid scaling view
                //  TODO ask designer to optimize animation file
                AvatarWithRipple(
                    modifier = Modifier
                        .padding(top = 87.dp)
                        .fillMaxWidth()
                        .align(Alignment.TopCenter),
                    userImageUrl = userImageUrl
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