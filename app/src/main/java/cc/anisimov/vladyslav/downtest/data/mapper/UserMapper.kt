package cc.anisimov.vladyslav.downtest.data.mapper

import cc.anisimov.vladyslav.downtest.data.network.model.UserApiModel
import cc.anisimov.vladyslav.downtest.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun map(userApiModel: UserApiModel): User {
        return with(userApiModel) {
            User(
                name = name,
                imageUrl = imageUrl
            )
        }
    }

}