package kr.co.pickup.Service


import com.google.gson.annotations.SerializedName

data class Service(
    @SerializedName("username")
    val username: String = "",
    @SerializedName("password")
    val password: String = "",
    @SerializedName("last_name")
    val last_name: String = "",
    @SerializedName("firts_name")
    val firts_name: String = "",
    @SerializedName("phone_number")
    val phone_number: String = "",
    @SerializedName("birth")
    val birth: String = "",
    @SerializedName("passwordConfirmation")
    val passwordConfirmation: String = "",
    @SerializedName("is_gym")
    val is_gym: Boolean = false
)