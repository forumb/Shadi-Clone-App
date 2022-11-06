package com.example.shaadicloneapp.data.db

import androidx.room.TypeConverter
import com.example.shaadicloneapp.data.model.Dob
import com.example.shaadicloneapp.data.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class DobConverter {
    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): Dob? {
        val listType: Type = object : TypeToken<Dob?>() {}.type
        return gson.fromJson<Dob?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: Dob?): String? {
        return gson.toJson(someObjects)
    }

}