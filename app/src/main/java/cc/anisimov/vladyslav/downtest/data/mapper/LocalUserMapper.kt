package cc.anisimov.vladyslav.downtest.data.mapper

import cc.anisimov.vladyslav.downtest.data.db.model.LocalUserDB
import cc.anisimov.vladyslav.downtest.domain.model.LocalUser
import javax.inject.Inject

class LocalUserMapper @Inject constructor() {

    fun map(localUserDB: LocalUserDB): LocalUser {
        return with(localUserDB) {
            LocalUser(
                spinCount = spinCount,
                imageUrl = imageUrl,
                location = location
            )
        }
    }

}