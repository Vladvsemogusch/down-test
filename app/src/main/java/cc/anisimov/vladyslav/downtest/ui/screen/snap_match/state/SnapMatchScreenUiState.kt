package cc.anisimov.vladyslav.downtest.ui.screen.snap_match.state


sealed class SnapMatchScreenUiState {
    abstract val spinCount: Int

    data class ReadyToMatch(
        override val spinCount: Int,
        val onlineUserCount: Int,
        val userImageUrl: String,
    ) : SnapMatchScreenUiState()


    companion object {
        val defaultState by lazy { ReadyToMatch(0, 0, "") }
    }
}
