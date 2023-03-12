package cc.anisimov.vladyslav.downtest.data.local

import cc.anisimov.vladyslav.downtest.data.ONLINE_USER_COUNT
import cc.anisimov.vladyslav.downtest.data.STARTING_SPIN_COUNT
import cc.anisimov.vladyslav.downtest.data.USER_IMAGE_URL
import cc.anisimov.vladyslav.downtest.data.db.model.LocalUserDB
import cc.anisimov.vladyslav.downtest.data.defaultUserLocation
import javax.inject.Inject

class LocalDataProvider @Inject constructor() {

    suspend fun getOnlineUserCount() = ONLINE_USER_COUNT

    suspend fun getLocalUser() = LocalUserDB(
        imageUrl = USER_IMAGE_URL,
        spinCount = STARTING_SPIN_COUNT,
        location = defaultUserLocation
    )
}