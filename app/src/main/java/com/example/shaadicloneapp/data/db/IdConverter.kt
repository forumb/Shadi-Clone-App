package com.example.shaadicloneapp.data.db

import androidx.room.TypeConverter
import com.example.shaadicloneapp.data.model.Id
import com.example.shaadicloneapp.data.model.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class IdConverter {
    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): Id? {
        val listType: Type = object : TypeToken<Id?>() {}.type
        return gson.fromJson<Id?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: Id?): String? {
        return gson.toJson(someObjects)
    }
}