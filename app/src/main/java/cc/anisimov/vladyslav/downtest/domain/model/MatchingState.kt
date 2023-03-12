package cc.anisimov.vladyslav.downtest.domain.model

sealed class MatchingState {

    data class InProgress(val numberInQueue: Int) : MatchingState()
    data class MatchFound(val user: User): MatchingState()
}