package data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrinkResponse(
    @SerialName("drinks")
    val drinkDTOS: List<DrinkDTO>
)