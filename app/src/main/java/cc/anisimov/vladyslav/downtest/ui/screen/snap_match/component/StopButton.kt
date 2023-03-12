package cc.anisimov.vladyslav.downtest.ui.screen.snap_match.component

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cc.anisimov.vladyslav.downtest.R
import cc.anisimov.vladyslav.downtest.ui.theme.DownTestTheme

@Composable
fun StopButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
    ) {
        Text(
            text = stringResource(id = R.string.snap_match_stop),
            style = MaterialTheme.typography.h4,
        )
    }
}

@Preview
@Composable
fun StopButtonPreview() {
    DownTestTheme {
        StopButton(onClick = {})
    }
}