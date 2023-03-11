package cc.anisimov.vladyslav.downtest.data.network.model


import com.google.gson.annotations.SerializedName

data class UserListApiModel(
    @SerializedName("35423")
    val userOne: UserApiModel,
    @SerializedName("49201")
    val userTwo: UserApiModel,
    @SerializedName("75423")
    val userThree: UserApiModel,
    @SerializedName("95935")
    val userFour: UserApiModel,
    @SerializedName("98508")
    val userFive: UserApiModel
)