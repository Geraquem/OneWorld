package com.mmfsin.oneworld.domain.interfaces

import android.content.Intent
import com.mmfsin.oneworld.domain.models.UpdateProfileData
import com.mmfsin.oneworld.domain.models.UserProfile
import kotlinx.coroutines.flow.Flow

interface IUsersRepository {
    fun checkIfUserLogged(): Boolean
    fun signInWithGoogle(): Intent
    suspend fun getOrCreateUser(name: String, email: String): UserProfile?

    fun getUserProfile(): Flow<UserProfile?>
    suspend fun editUserProfile(data: UpdateProfileData)

    fun closeSession()
}