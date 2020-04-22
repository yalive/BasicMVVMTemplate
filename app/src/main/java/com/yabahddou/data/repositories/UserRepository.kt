package com.yabahddou.data.repositories

import com.yabahddou.common.Result
import com.yabahddou.data.model.User
import com.yabahddou.data.net.RestApi
import com.yabahddou.data.net.RetrofitRunner
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 ***************************************
 * Created by Y.Abdelhadi on 4/22/20.
 ***************************************
 */
class UserRepository @Inject constructor(
    private val retrofitRunner: RetrofitRunner,
    private val restApi: RestApi
) {

    suspend fun getUser(): Result<User> {
        delay(2000) // Artificial delay to be removed!
        return retrofitRunner.executeNetworkCall {
            restApi.user()
        }
    }
}