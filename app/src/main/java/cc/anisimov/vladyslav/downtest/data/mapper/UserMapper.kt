package cc.anisimov.vladyslav.downtest.data.mapper

import cc.anisimov.vladyslav.downtest.data.DEFAULT_MATCH_LOCATION_NAME
import cc.anisimov.vladyslav.downtest.data.defaultMatchLocation
import cc.anisimov.vladyslav.downtest.data.network.model.UserApiModel
import cc.anisimov.vladyslav.downtest.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun map(userApiModel: UserApiModel): User {
        return with(userApiModel) {
            // Let's pretend UserApiModel also contains location and locationName
            val locationName = DEFAULT_MATCH_LOCATION_NAME
            val location = defaultMatchLocation
            User(
                name = name,
                imageUrl = imageUrl,
                locationName = locationName,
                location = location
            )
        }
    }
}