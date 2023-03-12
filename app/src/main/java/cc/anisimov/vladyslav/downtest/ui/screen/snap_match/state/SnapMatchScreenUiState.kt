package cc.anisimov.vladyslav.downtest.ui.screen.snap_match.state

import androidx.compose.runtime.Stable
import cc.anisimov.vladyslav.downtest.domain.model.User

@Stable
sealed class SnapMatchScreenUiState {
    abstract val spinCount: Int

    data class ReadyToMatch(
        override val spinCount: Int,
        val onlineUserCount: Int,
        val userImageUrl: String,
    ) : SnapMatchScreenUiState()

    data class MatchingPhaseOne(
        override val spinCount: Int,
        val userImageUrl: String
    ) : SnapMatchScreenUiState()

    data class MatchingPhaseTwo(
        override val spinCount: Int,
        val userImageUrl: String,
        val numberInQueue: Int
    ) : SnapMatchScreenUiState()

    data class MatchResult(
        override val spinCount: Int,
        val matchedUser: User
    ) : SnapMatchScreenUiState()

    companion object {
        val defaultState by lazy { ReadyToMatch(0, 0, "") }
    }
}
