package com.sharedtranscomp.ui.transition.animated_visibility

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharedtranscomp.R
import com.sharedtranscomp.data.FakeDataProvider
import com.sharedtranscomp.model.Snack
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme
import com.sharedtranscomp.ui.transition.animated_visibility.components.SnackContents


/**
 * Composable function for displaying the details content screen for a selected snack.
 * Handles shared element transitions for details content items.
 */
@Composable
fun SharedTransitionScope.SnackDetailScreen(
    modifier: Modifier = Modifier,
    snack: Snack,
    onSaveClick: () -> Unit
){

    AnimatedContent(
        modifier = modifier,
        targetState = snack,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        }
    ) { targetSnack ->

        Box(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp))
                    )
                    .clip(shape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp)))
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "${targetSnack.name}-bounds"),
                        animatedVisibilityScope = this@AnimatedContent,
                        clipInOverlayDuringTransition = OverlayClip(
                            clipShape = MaterialTheme.shapes.small.copy(all = CornerSize(15.dp))
                        )
                    )
            ) {
                SnackContents(
                    modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = targetSnack.name),
                            animatedVisibilityScope = this@AnimatedContent
                        )
                        .clickable(onClick = onSaveClick),
                    name = targetSnack.name,
                    image = targetSnack.image
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onSaveClick) {
                        Text(stringResource(R.string.save_changes))
                    }
                }
            }
        }
    }
}

//------------------------------------------------------------------//
//Preview//

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun SnackDetailScreenPreview(){

    val snack = FakeDataProvider.getSnacks()[0]

    SharedTransitionComposeTheme {
        SharedTransitionLayout {
            SnackDetailScreen(
                modifier = Modifier.fillMaxWidth(),
                snack = snack,
                onSaveClick = { /* Handle Click Action */ }
            )
        }
    }
}



