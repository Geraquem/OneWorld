package com.mmfsin.oneworld.data.repository

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.mmfsin.oneworld.data.ddbb.daos.UsersDAO
import com.mmfsin.oneworld.data.mappers.toUserProfile
import com.mmfsin.oneworld.data.models.UserProfileDTO
import com.mmfsin.oneworld.domain.interfaces.IUsersRepository
import com.mmfsin.oneworld.domain.models.UpdateProfileData
import com.mmfsin.oneworld.domain.models.UserProfile
import com.mmfsin.oneworld.utils.GOOGLE_AUTH
import com.mmfsin.oneworld.utils.USERS
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class UsersRepository @Inject constructor(
    @param:ApplicationContext val context: Context,
    private val usersDAO: UsersDAO
) : IUsersRepository {

    override fun checkIfLogged(): Boolean {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        return account != null
    }

    override fun signInWithGoogle(): Intent {
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(GOOGLE_AUTH)
            .requestEmail().build()
        return GoogleSignIn.getClient(context, googleConf).signInIntent
    }

    override suspend fun getOrCreateProfile(name: String, email: String) {
        var user: UserProfileDTO?

        val db = FirebaseFirestore.getInstance()
        val path = db.collection(USERS).document(email)
        val snapshot = path.get().await()

        if (snapshot.exists()) {
            /** get from Firestore */
            user = snapshot.toObject(UserProfileDTO::class.java)
        } else {
            /** Create new user */
            user = UserProfileDTO(
                id = UUID.randomUUID().toString(),
                email = email,
                name = name
            )

            /** insert in Firestore */
            path.set(user).await()
        }

        /** insert in Room */
        user?.let { usersDAO.insertUser(user) }
    }

    override fun getMyProfile(): Flow<UserProfile?> {
        return usersDAO.getActiveUserFlow().map { it?.toUserProfile() }
    }

    override suspend fun editMyProfile(data: UpdateProfileData) {
        val user = usersDAO.getActiveUser() ?: throw Exception("User not found")

        usersDAO.updateUser(
            id = user.id,
            name = data.name,
            imageUrl = data.imageUrl,
            biography = data.biography,
            website = data.website,
        )

        val db = FirebaseFirestore.getInstance()
        db.collection(USERS).document(user.email)
            .set(data, SetOptions.merge())
            .await()
    }

    override suspend fun closeSession() {
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(GOOGLE_AUTH)
            .requestEmail()
            .build()

        val client = GoogleSignIn.getClient(context, googleConf)
        client.signOut()
        usersDAO.closeSession()
    }
}