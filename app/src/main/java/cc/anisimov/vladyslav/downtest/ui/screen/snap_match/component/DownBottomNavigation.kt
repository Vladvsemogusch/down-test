package cc.anisimov.vladyslav.downtest.ui.screen.snap_match.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cc.anisimov.vladyslav.downtest.R
import cc.anisimov.vladyslav.downtest.ui.theme.unselectedGrey

@Composable
fun DownBottomNavigation(modifier: Modifier = Modifier) {
    BottomNavigation(
        modifier = modifier
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = { },
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
            onClick = { },
            selectedContentColor = MaterialTheme.colors.onPrimary,
            unselectedContentColor = MaterialTheme.colors.unselectedGrey,
            icon = {
                //  Icon wasn't provided had to use custom
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = null,
                )
            })
    }
}