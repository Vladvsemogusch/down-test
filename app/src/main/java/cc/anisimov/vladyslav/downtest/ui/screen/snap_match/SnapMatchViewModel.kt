package cc.anisimov.vladyslav.downtest.ui.screen.snap_match

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.anisimov.vladyslav.downtest.data.defaultUserLocation
import cc.anisimov.vladyslav.downtest.domain.interactor.GetLocalUserInteractor
import cc.anisimov.vladyslav.downtest.domain.interactor.GetOnlineUserCountInteractor
import cc.anisimov.vladyslav.downtest.domain.interactor.StartMatchingInteractor
import cc.anisimov.vladyslav.downtest.domain.model.LocalUser
import cc.anisimov.vladyslav.downtest.domain.model.MatchingState
import cc.anisimov.vladyslav.downtest.ui.screen.snap_match.state.SnapMatchScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SnapMatchViewModel @Inject constructor(
    private val getOnlineUserCountInteractor: GetOnlineUserCountInteractor,
    private val getLocalUserInteractor: GetLocalUserInteractor,
    private val startMatchingInteractor: StartMatchingInteractor,
) : ViewModel(), SnapMatchActions {

    private val _mapPositionFlow = MutableStateFlow(
        defaultUserLocation
    )
    val mapPositionFlow = _mapPositionFlow.asStateFlow()

    private val _uiStateFlow =
        MutableStateFlow<SnapMatchScreenUiState>(SnapMatchScreenUiState.defaultState)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    // Snapshot values on screen init, they will be constant for this screen instance
    private var onlineUserCount = 0
    private lateinit var localUser: LocalUser

    private var matchingJob: Job? = null

    init {
        viewModelScope.launch {
            val onlineUserCount = async { getOnlineUserCountInteractor() }
            val localUser = async { getLocalUserInteractor() }
            localUser.await().let {
                val newState = SnapMatchScreenUiState.ReadyToMatch(
                    spinCount = it.spinCount,
                    onlineUserCount = onlineUserCount.await(),
                    userImageUrl = it.imageUrl
                )
                this@SnapMatchViewModel.onlineUserCount = newState.onlineUserCount
                this@SnapMatchViewModel.localUser = it
                _uiStateFlow.emit(newState)
            }
        }
    }

    override fun startMatching() {
        if (localUser.spinCount == 0) return
        if (_uiStateFlow.value !is SnapMatchScreenUiState.ReadyToMatch) return
        matchingJob = viewModelScope.launch {
            _uiStateFlow.emit(
                SnapMatchScreenUiState.MatchingPhaseOne(
                    spinCount = localUser.spinCount,
                    userImageUrl = localUser.imageUrl
                )
            )
            val matchingStateFlow = startMatchingInteractor()
            matchingStateFlow.collect {
                handleMatchingState(it)
            }
        }
    }

    override fun stopMatching() {
        matchingJob?.cancel()
        //  Reset state
        viewModelScope.launch {
            _mapPositionFlow.emit(localUser.location)
            _uiStateFlow.emit(
                SnapMatchScreenUiState.ReadyToMatch(
                    spinCount = localUser.spinCount,
                    onlineUserCount = onlineUserCount,
                    userImageUrl = localUser.imageUrl
                )
            )
        }
    }

    private suspend fun handleMatchingState(matchingState: MatchingState) {
        when (matchingState) {
            is MatchingState.InProgress -> {
                _uiStateFlow.emit(
                    SnapMatchScreenUiState.MatchingPhaseTwo(
                        spinCount = localUser.spinCount,
                        userImageUrl = localUser.imageUrl,
                        numberInQueue = matchingState.numberInQueue
                    )
                )
            }
            is MatchingState.MatchFound -> {
                _mapPositionFlow.emit(matchingState.user.location)
                _uiStateFlow.emit(
                    SnapMatchScreenUiState.MatchResult(
                        spinCount = localUser.spinCount,
                        matchedUser = matchingState.user
                    )
                )
            }
        }
    }
}