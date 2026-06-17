package com.mmfsin.oneworld.data.ddbb.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mmfsin.oneworld.data.models.UserProfileDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userProfile: UserProfileDTO)

    @Query(
        """
        UPDATE table_users 
        SET 
        imageUrl = :imageUrl,
        name = :name,
        biography = :biography,
        website = :website
        WHERE id = :id
        """
    )
    suspend fun updateUser(
        id: String,
        imageUrl: String?,
        name: String,
        biography: String?,
        website: String?
    )

    @Query("SELECT * FROM table_users LIMIT 1")
    fun getActiveUserFlow(): Flow<UserProfileDTO?>

    @Query("SELECT * FROM table_users LIMIT 1")
    fun getActiveUser(): UserProfileDTO?

    @Query("SELECT * FROM table_users WHERE email = :email LIMIT 1")
    fun getUserByEmail(email: String): UserProfileDTO?

    @Query("DELETE FROM table_users")
    suspend fun closeSession()
}