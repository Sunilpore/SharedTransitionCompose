package com.sharedtranscomp.ui.transition.sheet

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharedtranscomp.R
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme
import com.sharedtranscomp.ui.transition.sheet.components.AlbumImage
import com.sharedtranscomp.ui.transition.sheet.components.AlbumPlayControls


/**
 * Composable function displaying the details content within the Sheet component.
 * Utilizes shared element transitions for smooth animations.
 */
@Composable
fun SharedTransitionScope.AlbumDetailScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope
){

    Card(
        modifier = modifier
    ) {

        Column(
            modifier = Modifier
                .padding(15.dp)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "bounds"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = shareBoundsTransform
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {

            AlbumImage(
                modifier = Modifier
                    .size(300.dp)
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(key = "image"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = shareBoundsTransform
                    )
                    .clip(MaterialTheme.shapes.small.copy(all = CornerSize(20.dp)))
            )
            Text(
                text = stringResource(R.string.angel_beach),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp
            )
            Text(
                text = stringResource(R.string.trilogy),
                fontSize = 18.sp
            )
            AlbumPlayControls(
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "icons"),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                playControlSize = 80.dp
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun AlbumDetailScreenPreview(){

    SharedTransitionComposeTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible =true) {
                AlbumDetailScreen(
                    animatedVisibilityScope = this
                )
            }
        }
    }
}