package com.sharedtranscomp.ui.transition.animated_visibility

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.core.tween


private const val ANIMATION_DURATION_IN_MILLIS = 500

/**
 * BoundsTransform for general shared element transitions
 * Tween animation applied to transition between initial and target bounds
 */
val snackBoundsTransition = BoundsTransform { _, _ ->
    sharedElementTransitionSpec()
}


/**
 * Define an animation specification with a tween animation of 500 milliseconds
 */
fun <T> sharedElementTransitionSpec() = tween<T>(durationMillis = ANIMATION_DURATION_IN_MILLIS)