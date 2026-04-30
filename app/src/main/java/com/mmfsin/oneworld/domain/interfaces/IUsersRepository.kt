package com.mmfsin.oneworld.domain.interfaces

import android.content.Intent
import com.mmfsin.oneworld.domain.models.UpdateProfileData
import com.mmfsin.oneworld.domain.models.UserProfile

interface IUsersRepository {
    fun checkIfUserLogged(): Boolean
    fun signInWithGoogle(): Intent
    suspend fun getOrCreateUser(name: String, email: String): UserProfile?

    suspend fun getUserProfile(): UserProfile?
    suspend fun editUserProfile(data: UpdateProfileData)
}