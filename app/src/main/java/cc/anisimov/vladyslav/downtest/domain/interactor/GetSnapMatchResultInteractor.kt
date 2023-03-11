package cc.anisimov.vladyslav.downtest.domain.interactor

import cc.anisimov.vladyslav.downtest.data.repository.DownRepository
import cc.anisimov.vladyslav.downtest.domain.model.User
import javax.inject.Inject

class GetSnapMatchResultInteractor @Inject constructor(private val repository: DownRepository) {

    suspend operator fun invoke(): User = repository.getSnapMatchResult()

}