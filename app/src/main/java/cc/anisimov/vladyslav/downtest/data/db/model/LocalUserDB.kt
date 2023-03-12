package cc.anisimov.vladyslav.downtest.data.db.model

import com.google.android.gms.maps.model.LatLng

data class LocalUserDB(
    val imageUrl: String,
    val spinCount: Int,
    val location: LatLng
)