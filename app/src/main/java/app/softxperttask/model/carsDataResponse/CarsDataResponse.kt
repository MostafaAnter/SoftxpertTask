package app.softxperttask.model.carsDataResponse


import com.google.gson.annotations.SerializedName

data class CarsDataResponse(
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("data")
    val `data`: List<Data> = listOf()
)