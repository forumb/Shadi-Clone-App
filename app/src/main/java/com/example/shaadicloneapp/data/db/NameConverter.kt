package com.example.shaadicloneapp.data.db

import androidx.room.TypeConverter
import com.example.shaadicloneapp.data.model.Location
import com.example.shaadicloneapp.data.model.Name
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class NameConverter {
    var gson = Gson()
    @TypeConverter
    fun stringToSomeObjectList(data: String?): Name? {
        val listType: Type = object : TypeToken<Name?>() {}.type
        return gson.fromJson<Name?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: Name?): String? {
        return gson.toJson(someObjects)
    }
}