package com.example.shaadicloneapp.data.db

import androidx.room.TypeConverter
import com.example.shaadicloneapp.data.model.Dob
import com.example.shaadicloneapp.data.model.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LocationConverter {
    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): Location? {
        val listType: Type = object : TypeToken<Location?>() {}.type
        return gson.fromJson<Location?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: Location?): String? {
        return gson.toJson(someObjects)
    }
}