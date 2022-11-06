package com.example.shaadicloneapp.data.model


import com.google.gson.annotations.SerializedName

data class UserListDto(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: ArrayList<User>
) {
    data class Info(
        @SerializedName("page")
        val page: Int,
        @SerializedName("results")
        val results: Int,
        @SerializedName("seed")
        val seed: String,
        @SerializedName("version")
        val version: String
    )

}