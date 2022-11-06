package com.example.shaadicloneapp.data.db

import androidx.room.Database
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shaadicloneapp.data.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DobConverter::class, LocationConverter::class, IdConverter::class, PictureConverter::class, NameConverter::class)
abstract class UserDatabase: RoomDatabase(){
    abstract fun getUserDao(): UserDao
}