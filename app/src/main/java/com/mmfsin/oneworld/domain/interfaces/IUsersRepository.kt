package com.mmfsin.oneworld.domain.interfaces

import android.content.Intent
import com.mmfsin.oneworld.domain.models.UpdateProfileData
import com.mmfsin.oneworld.domain.models.UserProfile
import kotlinx.coroutines.flow.Flow

interface IUsersRepository {
    fun checkIfLogged(): Boolean
    fun signInWithGoogle(): Intent
    suspend fun getOrCreateProfile(name: String, email: String)

    fun getMyProfile(): Flow<UserProfile?>
    suspend fun editMyProfile(data: UpdateProfileData)

    suspend fun closeSession()
}