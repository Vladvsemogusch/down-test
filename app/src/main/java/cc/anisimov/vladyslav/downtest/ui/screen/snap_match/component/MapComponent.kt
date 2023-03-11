package cc.anisimov.vladyslav.downtest.ui.screen.snap_match.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import cc.anisimov.vladyslav.downtest.R
import cc.anisimov.vladyslav.downtest.ui.base.component.rememberMapViewWithLifecycle
import com.google.android.gms.maps.model.MapStyleOptions

@Composable
fun MapComponent(modifier: Modifier = Modifier) {
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