package com.sharedtranscomp.ui.transition.sheet

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Rect


private const val ANIMATION_DURATION_IN_MILLIS = 700

val shareBoundsTransform = BoundsTransform { _: Rect, _:Rect ->
    tween(
        durationMillis = ANIMATION_DURATION_IN_MILLIS,
        easing = FastOutSlowInEasing
    )


}