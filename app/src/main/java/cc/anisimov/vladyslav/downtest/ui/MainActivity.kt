package cc.anisimov.vladyslav.downtest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import cc.anisimov.vladyslav.downtest.ui.screen.snap_match.SnapMatchRoute
import cc.anisimov.vladyslav.downtest.ui.screen.snap_match.SnapMatchViewModel
import cc.anisimov.vladyslav.downtest.ui.theme.DownTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SnapMatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DownTestTheme {
                SnapMatchRoute(viewModel = viewModel)
            }
        }
    }
}