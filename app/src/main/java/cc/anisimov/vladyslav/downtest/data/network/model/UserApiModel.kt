package cc.anisimov.vladyslav.downtest.data.network.model


import com.google.gson.annotations.SerializedName

data class UserApiModel(
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("retail_price")
    val retailPrice: Int
)