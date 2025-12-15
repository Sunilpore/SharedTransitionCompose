package com.sharedtranscomp.model

import androidx.annotation.DrawableRes

data class Snack (
    val name:String,
    @DrawableRes val image:Int
)