package com.sharedtranscomp.ui.transition.animated_visibility


import android.content.res.Configuration
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharedtranscomp.R
import com.sharedtranscomp.data.FakeDataProvider
import com.sharedtranscomp.model.Snack
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme


/**
 * Composable function for shared element transition demo with AnimatedVisibility.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TransitionWithAnimatedVisibilityScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
){

    var selectedSnack by remember { mutableStateOf<Snack?>(null) }
    var snacks = remember (Unit){ FakeDataProvider.getSnacks() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.animated_visibility_shared_element))
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
    ) { paddingValues ->
        MainContent(
            modifier = Modifier.padding(paddingValues),
            snacks = snacks,
            selectedSnack = selectedSnack,
            onSelectedSnack = { snack ->
                selectedSnack = snack
            },
            onSaveClick = {
                selectedSnack = null
            }
        )
    }
}


/**
 * Composable function for displaying a list of snacks with shared element transitions.
 * Applies animations and blur effect when a snack is selected.
 */
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    snacks: List<Snack>,
    selectedSnack: Snack?,
    onSelectedSnack: (Snack) -> Unit,
    onSaveClick: () -> Unit
){
    SharedTransitionLayout (
        modifier = modifier
    ){
        LazyColumn(
            modifier = Modifier
                .background(Color.LightGray.copy(alpha = 0.5f))
                .then(if(selectedSnack !=null) Modifier.blur(10.dp) else Modifier)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(snacks){ _, snack ->
                SnackItem(
                    modifier = Modifier.animateItem(
                        placementSpec = sharedElementTransitionSpec(),
                        fadeOutSpec = sharedElementTransitionSpec(),
                        fadeInSpec = sharedElementTransitionSpec()
                    ),
                    snack = snack,
                    visible = selectedSnack != snack,
                    onClick = {
                        onSelectedSnack(snack)
                    }
                )
            }
        }

        if(selectedSnack !=null){
            SnackDetailScreen(
                modifier = Modifier.fillMaxSize(),
                snack = selectedSnack,
                onSaveClick = onSaveClick
            )
        }
    }
}


//------------------------------------------------------------------//
//Preview//

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true)
private fun MainContentPreview(){

    SharedTransitionComposeTheme {
        MainContent(
            snacks = FakeDataProvider.getSnacks(),
            selectedSnack = null,
            onSelectedSnack = { /* Handle Click Action */ },
            onSaveClick = { /* Handle Click Action */ }
        )
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TransitionWithoutNavigationScreenPreview(){

    SharedTransitionComposeTheme {
        TransitionWithAnimatedVisibilityScreen(
            onBack = { /* Handle Click Action */ }
        )
    }
}
