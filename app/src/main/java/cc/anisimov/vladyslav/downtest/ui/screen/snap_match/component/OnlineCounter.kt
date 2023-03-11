package cc.anisimov.vladyslav.downtest.ui.screen.snap_match.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cc.anisimov.vladyslav.downtest.R
import cc.anisimov.vladyslav.downtest.ui.theme.DownTestTheme
import cc.anisimov.vladyslav.downtest.ui.theme.indicatorGreen

@Composable
fun OnlineCounter(modifier: Modifier = Modifier, usersOnlineCount: Int) {
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
                text = stringResource(R.string.snap_match_online_counter, usersOnlineCount),
                style = MaterialTheme.typography.h5,
            )
        }
    }
}
@Composable
fun OnlineIndicator(modifier: Modifier = Modifier) {
    val indicatorColor = MaterialTheme.colors.indicatorGreen
    Canvas(modifier = modifier, onDraw = {
        drawCircle(color = indicatorColor)
    })
}

@Preview
@Composable
fun OnlineCounterPreview() {
    DownTestTheme {
        OnlineCounter(usersOnlineCount = 4)
    }
}