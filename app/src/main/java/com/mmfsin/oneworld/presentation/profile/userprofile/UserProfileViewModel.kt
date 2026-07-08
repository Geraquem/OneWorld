package com.mmfsin.oneworld.presentation.profile.userprofile

import com.mmfsin.oneworld.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
) : BaseViewModel<UserProfileStates>(UserProfileStates()) {

    init {
    }

}