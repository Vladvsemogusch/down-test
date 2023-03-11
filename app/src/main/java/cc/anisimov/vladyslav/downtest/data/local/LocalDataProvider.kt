package cc.anisimov.vladyslav.downtest.data.local

import javax.inject.Inject

class LocalDataProvider @Inject constructor(){

    suspend fun getOnlineUserCount() = 10283

    suspend fun getSpinCount() = 4

    suspend fun  getUserImageUrl() = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
}