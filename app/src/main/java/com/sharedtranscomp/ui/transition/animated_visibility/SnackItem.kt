package com.sharedtranscomp.ui.transition.animated_visibility

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharedtranscomp.data.FakeDataProvider
import com.sharedtranscomp.model.Snack
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme
import com.sharedtranscomp.ui.transition.animated_visibility.components.SnackContents


/**
 * Composable function for displaying a snack item with shared element transition.
 * Includes animations for visibility changes.
 */
@Composable
fun SharedTransitionScope.SnackItem(
    modifier: Modifier = Modifier,
    snack: Snack,
    visible: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(animationSpec = sharedElementTransitionSpec()) + scaleIn(
            sharedElementTransitionSpec()
        ),
        exit = fadeOut(animationSpec = sharedElementTransitionSpec()) + scaleOut(
            sharedElementTransitionSpec()
        )
    ) {
        Box(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "${snack.name}-bounds"),
                    animatedVisibilityScope = this@AnimatedVisibility,
                    boundsTransform = snackBoundsTransition,
                    clipInOverlayDuringTransition = OverlayClip(
                        clipShape = MaterialTheme.shapes.small.copy(CornerSize(15.dp))
                    )
                )
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp))
                )
                .clip(shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp)))
        ) {
            SnackContents(
                modifier = Modifier
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(key = snack.name),
                        animatedVisibilityScope = this@AnimatedVisibility,
                        boundsTransform = snackBoundsTransition
                    )
                    .clickable(onClick = onClick),
                name = snack.name,
                image = snack.image
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun SnackItemPreview(){

    val snack = FakeDataProvider.getSnacks()[0]

    SharedTransitionComposeTheme {
        SharedTransitionLayout {
            SnackItem(
                snack = snack,
                visible = true,
                onClick = { /* Handle Click Action*/ }
            )
        }
    }
}


