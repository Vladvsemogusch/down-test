package cc.anisimov.vladyslav.downtest.domain.model

import com.google.android.gms.maps.model.LatLng

data class LocalUser(
    val imageUrl: String,
    val spinCount: Int,
    val location: LatLng
)