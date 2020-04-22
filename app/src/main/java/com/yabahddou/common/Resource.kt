package com.yabahddou.common

import androidx.annotation.StringRes

/**
 * State description
 */
sealed class Resource<out T> {

    object Loading : Resource<Nothing>()

    data class Success<out T>(val data: T) : Resource<T>()

    data class Failure(val message: AppMessage) : Resource<Nothing>()
}

fun <T> errorMessage(@StringRes resId: Int): Resource<T> {
    return Resource.Failure(AppMessage.ResourceMessage(resId))
}