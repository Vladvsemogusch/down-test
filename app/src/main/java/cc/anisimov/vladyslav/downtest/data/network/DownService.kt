package cc.anisimov.vladyslav.downtest.data.network

import cc.anisimov.vladyslav.downtest.data.network.model.UserListApiModel
import retrofit2.http.GET

interface DownService {

    @GET("v3/4e23865c-b464-4259-83a3-061aaee400ba")
    suspend fun getSnapMatchUserList(): UserListApiModel
}