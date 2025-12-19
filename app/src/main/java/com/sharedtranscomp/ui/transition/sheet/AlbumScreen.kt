package com.sharedtranscomp.ui.transition.sheet

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharedtranscomp.R
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme
import com.sharedtranscomp.ui.transition.sheet.components.AlbumDetails
import com.sharedtranscomp.ui.transition.sheet.components.AlbumImage
import com.sharedtranscomp.ui.transition.sheet.components.AlbumPlayControls


/**
 * Composable function for the main content displayed in the Sheet component.
 * Uses shared element transitions for smooth animations.
 */
@Composable
fun SharedTransitionScope.AlbumScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope
){

    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {

            AlbumImage(
                modifier = Modifier
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(key = "image"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = shareBoundsTransform
                    )
                    .size(height = 100.dp, width = 90.dp)
                    .clip(MaterialTheme.shapes.small.copy(all = CornerSize(20.dp)))
            )
            AlbumDetails()
            AlbumPlayControls(
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "icons"),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                playControlSize = 30.dp
            )
        }
    }

}


//------------------------------------------------------------------//
//Preview//

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumScreenPreview(){

    SharedTransitionComposeTheme {
        SharedTransitionLayout {
            AnimatedVisibility(true) {
                AlbumScreen(
                    animatedVisibilityScope = this
                )
            }
        }
    }
}






