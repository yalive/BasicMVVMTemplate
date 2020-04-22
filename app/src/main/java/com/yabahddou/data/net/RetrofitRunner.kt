package com.yabahddou.data.net

import com.google.gson.Gson
import com.yabahddou.R
import com.yabahddou.common.AppMessage
import com.yabahddou.common.Result
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofitRunner @Inject constructor(
    private val gson: Gson
) {

    suspend fun <T> executeNetworkCall(
        request: suspend () -> T
    ): Result<T> = try {
        val response = request()
        Result.Success(response)
    } catch (e: Exception) {
        errorFromException(e, gson)
    }

    suspend fun <T> executeNetworkWithStatus(request: suspend () -> Response<T>): Result<T> =
        try {
            val response = request()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.Success(body)
            } else {
                errorFromException(
                    HttpException(response),
                    gson
                )
            }
        } catch (e: Exception) {
            errorFromException(e, gson)
        }
}

private fun errorFromException(exception: Exception, gson: Gson): Result.Error {
    var message: AppMessage = AppMessage.ResourceMessage(R.string.common_technical_issue)
    // We try to get some information about the error
    if (exception is HttpException) {
        try {
            val serverErrorStr = exception.response()?.errorBody()?.string()
            if (serverErrorStr != null) {
                val errorRS = gson.fromJson(serverErrorStr, ServerErrorRS::class.java)
                if (errorRS.code != null && errorRS.description != null) {
                    message = AppMessage.StringMessage(errorRS.description)
                }
            }
        } catch (e: Exception) {
            // Nothing to do (Unable to parse error)
        }
    } else if (exception is UnknownHostException) {
        message = AppMessage.ResourceMessage(R.string.common_check_internet_connection)
    }
    return Result.Error(message)
}