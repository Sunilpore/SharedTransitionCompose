package com.sharedtranscomp.ui.transition.fab

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.sharedtranscomp.R
import com.sharedtranscomp.data.FakeDataProvider
import com.sharedtranscomp.model.Profile
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme


@Composable
fun TransitionWithFabComponentScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
){

    var showDetails by remember { mutableStateOf(false) }
    val profiles = remember(Unit) { FakeDataProvider.getFabProfiles() }


    Scaffold(
        modifier = modifier,
        topBar = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->
        MainContent(
            modifier = Modifier.padding(paddingValues),
            profiles = profiles,
            showDetails = showDetails,
            onShowDetails = {
                showDetails = true
            },
            onBack = {
                showDetails = false
            }
        )
    }
}



/**
 * Composable function demonstrating a FAB component animation.
 * Manages the state to show details using SharedTransitionLayout and AnimatedContent.
 */
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    profiles: List<Profile>,
    showDetails: Boolean,
    onShowDetails: () -> Unit,
    onBack: () -> Unit
){

    SharedTransitionLayout(
        modifier = modifier
    ) {
        AnimatedContent(
            targetState = showDetails,
            label = "basic_transition"
        ) { targetState ->

            if(!targetState){
                FabMainContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "bounds"),
                            animatedVisibilityScope = this@AnimatedContent,
                            enter = fabEnterAnimation,
                            exit = fabExitAnimation,
                            boundsTransform = fabBoundTransform
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onShowDetails
                        ),
                    animatedVisibilityScope = this@AnimatedContent
                )
            } else {
                FabDetailsContent(
                    modifier = Modifier.fillMaxSize(),
                    animatedVisibilityScope = this@AnimatedContent,
                    profiles = profiles,
                    onBack = onBack
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
private fun TransitionWithFabComponentScreenPreview(){

    SharedTransitionComposeTheme {
        TransitionWithFabComponentScreen(
            modifier = Modifier.fillMaxSize(),
            onBack = {/* Handle Click Action */ }
        )
    }

}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MainContentPreview(){

    SharedTransitionComposeTheme {
        MainContent(
            profiles = FakeDataProvider.getFabProfiles(),
            showDetails = true,
            onShowDetails = { /* Handle Click Action */ },
            onBack = { /* Handle Click Action */ }
        )
    }
}