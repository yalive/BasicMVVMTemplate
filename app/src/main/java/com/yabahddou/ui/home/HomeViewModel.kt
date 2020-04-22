package com.yabahddou.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yabahddou.common.BaseViewModel
import com.yabahddou.common.Resource
import com.yabahddou.common.asResource
import com.yabahddou.data.model.User
import com.yabahddou.data.repositories.UserRepository
import javax.inject.Inject

/**
 ***************************************
 * Created by Y.Abdelhadi on 4/22/20.
 ***************************************
 */
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _user = MutableLiveData<Resource<User>>()
    val user: LiveData<Resource<User>>
        get() = _user

    fun fetchUser() = uiCoroutine {
        _user.value = Resource.Loading
        val userResult = userRepository.getUser()
        // convert result to resource thus ui will interpret it
        _user.value = userResult.asResource()
    }
}