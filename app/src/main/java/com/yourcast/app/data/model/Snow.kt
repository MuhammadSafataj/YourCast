package com.yourcast.app.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Snow(
    @SerializedName("1h")
    @Expose
    val one: Int,

    @SerializedName("3h")
    @Expose
    val three: Int
)
