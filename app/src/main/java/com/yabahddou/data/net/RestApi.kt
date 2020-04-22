package com.yabahddou.data.net

import com.yabahddou.common.event.Constants
import com.yabahddou.data.model.User
import retrofit2.http.GET

/**
 ***************************************
 * Created by Y.Abdelhadi on 4/22/20.
 ***************************************
 */
interface RestApi {
    @GET(Constants.WS.API_PROFILE)
    suspend fun user(): User
}