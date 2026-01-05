package com.sharedtranscomp.ui.transition.fab

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharedtranscomp.R
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme




@Composable
fun SharedTransitionScope.FabMainContent(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope
){

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {

        Card(
            modifier = Modifier
                .padding(end = 30.dp, bottom = 40.dp)
                .size(80.dp)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "card_bounds"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    enter = fabEnterAnimation,
                    exit = fabExitAnimation,
                    boundsTransform = fabBoundTransform
                )
                .clip(MaterialTheme.shapes.small.copy(all = CornerSize(20.dp))),
            colors = CardDefaults.cardColors(containerColor = Color.Cyan),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

        ) {
            Icon(
                modifier = Modifier.padding(20.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                contentDescription = null
            )
        }
    }

}


//------------------------------------------------------------------//
//Preview//

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun FabMainContentPreview(){

    SharedTransitionComposeTheme {
        SharedTransitionLayout {
            AnimatedVisibility(true) {
                FabMainContent(
                    modifier = Modifier.fillMaxSize(),
                    animatedVisibilityScope = this
                )
            }
        }
    }
}
