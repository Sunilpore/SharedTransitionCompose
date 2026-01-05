package com.sharedtranscomp.model

import androidx.annotation.DrawableRes

data class Profile(
    val id:Int,
    val name:String,
    @DrawableRes val img: Int,
)