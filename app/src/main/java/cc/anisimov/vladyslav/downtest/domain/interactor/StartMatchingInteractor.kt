package cc.anisimov.vladyslav.downtest.domain.interactor

import cc.anisimov.vladyslav.downtest.data.repository.DownRepository
import javax.inject.Inject

class StartMatchingInteractor @Inject constructor(private val repository: DownRepository) {

    operator fun invoke() = repository.startMatching()

}