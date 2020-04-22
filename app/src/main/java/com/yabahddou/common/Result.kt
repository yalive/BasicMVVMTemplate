package com.yabahddou.common

import androidx.annotation.StringRes
import com.yabahddou.R

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(
        val message: AppMessage,
        val detail: ErrorDetail = ErrorDetail()
    ) : Result<Nothing>()
}

// Add more detail if needed
class ErrorDetail

sealed class AppMessage {
    data class ResourceMessage(
        @StringRes val resMessage: Int,
        val arguments: List<String> = emptyList()
    ) : AppMessage()

    data class StringMessage(val message: String) : AppMessage()
}

fun <T> Result<T>.asResource(): Resource<T> {
    return when (this) {
        is Result.Success -> Resource.Success(data)
        is Result.Error -> Resource.Failure(message)
    }
}

suspend fun <I, O> Result<I>.map(mapSuccess: suspend (I) -> O): Result<O> {
    return when (this) {
        is Result.Success -> Result.Success(mapSuccess(data))
        is Result.Error -> this
    }
}

suspend fun <T> Result<T>.alsoWhenSuccess(doOnSuccess: suspend (T) -> Unit): Result<T> {
    if (this is Result.Success) doOnSuccess(data)
    return this
}

val TECHNICAL_ISSUE_MESSAGE = AppMessage.ResourceMessage(R.string.common_technical_issue)
val TECHNICAL_ISSUE_RESULT = Result.Error(TECHNICAL_ISSUE_MESSAGE)
val NO_RESULT = Result.Error(AppMessage.ResourceMessage(R.string.common_empty_state))