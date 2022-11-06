package com.example.shaadicloneapp.data.db

import androidx.room.TypeConverter
import com.example.shaadicloneapp.data.model.Location
import com.example.shaadicloneapp.data.model.Picture
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class PictureConverter {
    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): Picture? {
        val listType: Type = object : TypeToken<Picture?>() {}.type
        return gson.fromJson<Picture?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: Picture?): String? {
        return gson.toJson(someObjects)
    }
}