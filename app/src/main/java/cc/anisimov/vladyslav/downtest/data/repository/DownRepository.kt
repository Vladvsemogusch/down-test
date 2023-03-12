package cc.anisimov.vladyslav.downtest.data.repository

import cc.anisimov.vladyslav.downtest.data.local.LocalDataProvider
import cc.anisimov.vladyslav.downtest.data.mapper.LocalUserMapper
import cc.anisimov.vladyslav.downtest.data.mapper.UserMapper
import cc.anisimov.vladyslav.downtest.data.network.DownService
import cc.anisimov.vladyslav.downtest.domain.model.MatchingState
import cc.anisimov.vladyslav.downtest.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

//  localDataProvider could be injected as an interface which is beyond the scope of this task
class DownRepository @Inject constructor(
    private val localDataProvider: LocalDataProvider,
    private val downService: DownService,
    private val userMapper: UserMapper,
    private val localUserMapper: LocalUserMapper
) {

    suspend fun getOnlineUserCount() = localDataProvider.getOnlineUserCount()
    suspend fun getLocalUser() = localUserMapper.map(localDataProvider.getLocalUser())

    private suspend fun getSnapMatchResult(): User = withContext(Dispatchers.IO) {
        // Wait 5-10 sec according to task requirements
        val delayDurationRange = 5000L..10000L
        delay(delayDurationRange.random())
        val userApiModel = downService.getSnapMatchUserList().userFour
        userMapper.map(userApiModel)
    }

    fun startMatching(): Flow<MatchingState> {
        // Simulate matching
        return flow {
            delay(2000)
            emit(MatchingState.InProgress(numberInQueue = 10))
            val snapMatchResult = getSnapMatchResult()
            emit(MatchingState.MatchFound(user = snapMatchResult))
        }.flowOn(Dispatchers.IO)
    }

}