package com.yabahddou.common.extensions

import androidx.fragment.app.Fragment
import com.yabahddou.common.AppMessage
import com.yabahddou.common.extensions.longToast
import com.yabahddou.common.extensions.messageFrom
import com.yabahddou.common.extensions.toast
import com.yabahddou.common.extensions.toastCentred

fun Fragment.messageFrom(appMessage: AppMessage) = requireContext().messageFrom(appMessage)

fun Fragment.toast(message: String) {
    requireContext().toast(message)
}

fun Fragment.longToast(message: String) {
    requireContext().longToast(message)
}

fun Fragment.toastCentred(message: String) {
    requireContext().toastCentred(message)
}