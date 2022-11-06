package com.example.shaadicloneapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("colId")
    var colId: Int = 0,
    @SerializedName("id")
    val id: Id,
    @SerializedName("cell")
    val cell: String,
    @SerializedName("dob")
    val dob: Dob,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: Name,
    @SerializedName("nat")
    val nat: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("picture")
    val picture: Picture,
    @SerializedName("hasAccepted")
    var hasAccepted: Boolean = false,
    @SerializedName("hasDeclined")
    var hasDeclined: Boolean = false,
    @SerializedName("message")
    var message: String? = ""
)