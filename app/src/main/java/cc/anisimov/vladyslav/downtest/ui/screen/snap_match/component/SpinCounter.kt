package cc.anisimov.vladyslav.downtest.ui.screen.snap_match.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cc.anisimov.vladyslav.downtest.R
import cc.anisimov.vladyslav.downtest.ui.theme.DownTestTheme

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

@Preview
@Composable
fun SpinCounterPreview() {
    DownTestTheme {
        SpinCounter(spinCount = 4)
    }
}