package com.sharedtranscomp.ui.transition.text_transform
import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharedtranscomp.R
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme


/**
 * Composable function for the shared element transition demo text component
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransitionWithTextTransformScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
){

    var showDetails by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.text_transform_animation))
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ){ paddingValues ->
        MainContent(
            modifier = Modifier.padding(paddingValues),
            showDeatils = showDetails,
            onShowDetails = {
                showDetails = true
            },
            onBack = {
                showDetails = false
            }
        )
    }

}


@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    showDeatils: Boolean,
    onShowDetails: () -> Unit,
    onBack: () -> Unit
){

    val roundedBoxModifier = Modifier
        .padding(15.dp)
        .border(
            width = 1.dp,
            color = Color.Gray,
            shape = MaterialTheme.shapes.small.copy(all = CornerSize(8.dp))
        )
        .background(
            color = Color.LightGray,
            shape = MaterialTheme.shapes.small.copy(all = CornerSize(8.dp))
        )

    SharedTransitionLayout(
        modifier = modifier
    ) {
        AnimatedContent(
            targetState = showDeatils,
            label = "basic_transition"
        ) { targetState ->

            val sharedBoundsModifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "bounds"),
                    animatedVisibilityScope = this@AnimatedContent,
                    enter = textEnterAnimation,
                    exit = textExitAnimation,
                    boundsTransform = textBoundsTransform
            )

            if(!targetState){
                Emoji(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(roundedBoxModifier)
                        .then(sharedBoundsModifier)
                        .clickable(onClick = onShowDetails),
                    animatedVisibilityScope = this@AnimatedContent
                )
            } else {
                EmojiDetails(
                    modifier = Modifier
                        .then(roundedBoxModifier)
                        .then(sharedBoundsModifier)
                        .clickable(onClick = onBack),
                    animatedVisibilityScope = this@AnimatedContent
                )
            }
        }
    }

}

//------------------------------------------------------------------//
//Preview//


@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MainContentPreview(){
    SharedTransitionComposeTheme {
        MainContent(
            showDeatils = false,
            onShowDetails = {/*Handle Click Action*/},
            onBack = {/*Handle Click Action*/}
        )
    }
}


@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MainContentWithShowDetails(){
    SharedTransitionLayout {
        MainContent(
            showDeatils = true,
            onShowDetails = {/*Handle Click Action*/},
            onBack = {/*Handle Click Action*/}
        )
    }
}
