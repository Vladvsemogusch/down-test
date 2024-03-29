package cc.anisimov.vladyslav.downtest.domain.model

import com.google.android.gms.maps.model.LatLng

data class User(
    val name: String,
    val imageUrl: String,
    val locationName: String,
    val location: LatLng
)