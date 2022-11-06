package com.example.shaadicloneapp.data.db

import androidx.room.*
import com.example.shaadicloneapp.data.model.Id
import com.example.shaadicloneapp.data.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUsers(user: List<User>)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("UPDATE users SET hasAccepted=:hasAccepted WHERE email = :email")
    suspend fun updateAcceptedField(hasAccepted: Boolean?, email: String)

    @Query("UPDATE users SET hasDeclined=:hasDeclined WHERE email = :email")
    suspend fun updateDeclineField(hasDeclined: Boolean?, email: String)
}