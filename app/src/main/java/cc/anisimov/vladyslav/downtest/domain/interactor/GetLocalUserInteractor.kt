package cc.anisimov.vladyslav.downtest.domain.interactor

import cc.anisimov.vladyslav.downtest.data.repository.DownRepository
import cc.anisimov.vladyslav.downtest.domain.model.LocalUser
import javax.inject.Inject

class GetLocalUserInteractor @Inject constructor(private val repository: DownRepository) {

    suspend operator fun invoke(): LocalUser = repository.getLocalUser()

}