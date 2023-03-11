package cc.anisimov.vladyslav.downtest.domain.interactor

import cc.anisimov.vladyslav.downtest.data.repository.DownRepository
import javax.inject.Inject

class GetSpinCountInteractor @Inject constructor(private val repository: DownRepository) {

    suspend operator fun invoke(): Int = repository.getSpinCount()

}