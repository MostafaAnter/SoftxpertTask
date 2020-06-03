package app.softxperttask.model.carsDataResponse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("brand")
    val brand: String = "",
    @SerializedName("constractionYear")
    val constractionYear: String = "",
    @SerializedName("isUsed")
    val isUsed: Boolean = false,
    @SerializedName("imageUrl")
    val imageUrl: String = ""
)