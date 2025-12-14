package com.sharedtranscomp.ui.transition.text_transform

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharedtranscomp.R
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme


@Composable
fun SharedTransitionScope.EmojiDetails(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope
){
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "image"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = textBoundsTransform
                )
                .size(200.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.dp10),
            contentDescription = stringResource(R.string.emojis)
        )

        Text(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "title"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = animatedTextBoundsTransform
                ),
            text = stringResource(R.string.emojis),
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            modifier = Modifier.skipToLookaheadSize(),
            color = MaterialTheme.colorScheme.onSurface,
            text = stringResource(id = R.string.album_description)
        )
    }
}


@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun EmojiDetailsPreview(){

    SharedTransitionComposeTheme {
        SharedTransitionLayout {
            AnimatedVisibility(true) {
                EmojiDetails(animatedVisibilityScope = this@AnimatedVisibility)
            }
        }
    }
}