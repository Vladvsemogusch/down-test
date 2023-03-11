package cc.anisimov.vladyslav.downtest.ui.screen.snap_match

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.anisimov.vladyslav.downtest.domain.interactor.*
import cc.anisimov.vladyslav.downtest.ui.screen.snap_match.state.SnapMatchScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SnapMatchViewModel @Inject constructor(
    private val getOnlineUserCountInteractor: GetOnlineUserCountInteractor,
    private val getSpinCountInteractor: GetSpinCountInteractor,
    private val consumeSpinInteractor: ConsumeSpinInteractor,
    private val getUserImageUrlInteractor: GetUserImageUrlInteractor,
    private val getSnapMatchResultInteractor: GetSnapMatchResultInteractor
) : ViewModel() {


    private val _uiStateFlow =
        MutableStateFlow<SnapMatchScreenUiState>(SnapMatchScreenUiState.defaultState)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val onlineUserCount = async { getOnlineUserCountInteractor() }
            val spinCount = async { getSpinCountInteractor() }
            val userImageUrl = async { getUserImageUrlInteractor() }
            val newState = SnapMatchScreenUiState.ReadyToMatch(
                spinCount = spinCount.await(),
                onlineUserCount = onlineUserCount.await(),
                userImageUrl = userImageUrl.await()
            )
            _uiStateFlow.emit(newState)
        }
    }
}