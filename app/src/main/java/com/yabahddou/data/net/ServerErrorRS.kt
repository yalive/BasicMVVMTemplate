package com.yabahddou.data.net

import com.google.gson.annotations.SerializedName

data class ServerErrorRS(
    @SerializedName("code")
    val code: String?,
    @SerializedName("description")
    val description: String?
)