package com.sharedtranscomp.ui.transition.without_navigation

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharedtranscomp.R
import com.sharedtranscomp.data.FakeDataProvider
import com.sharedtranscomp.model.Coffee
import com.sharedtranscomp.navigation.Screens
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme

/**
 * Transformation for the shared element bounds.
 * Defines the tween animation for the shared element transitions.
 */
private val boundsTransform = { _: Rect, _: Rect ->
    tween<Rect>(500)
}


/**
 * Composable function for the shared element transition demo without navigation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransitionWithoutNavigationScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
){

    var showDetails by remember { mutableStateOf(false)}
    var selectedCoffeeId by remember { mutableIntStateOf(-1) }
    var coffeeList = remember(Unit) { FakeDataProvider.getCoffees() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.shared_element_transition_without_navigation))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        content = {
                            Icon(ImageVector.vectorResource(R.drawable.ic_arrow_back), contentDescription = null)
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        MainContent(
            modifier = Modifier.padding(paddingValues),
            coffees = coffeeList,
            showDetails = showDetails,
            selectedCoffeeId = selectedCoffeeId,
            onCoffeeClick = { coffee ->
                selectedCoffeeId = coffee.id
                showDetails = true
            },
            onBackClick = {
                showDetails = false
            }
        )

    }

}


/**
 * Composable function that sets up the navigation and shared element transitions
 * for the coffee preview and detail screens.
 */
@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    coffees:List<Coffee>,
    showDetails: Boolean,
    selectedCoffeeId:Int,
    onCoffeeClick: (Coffee) -> Unit,
    onBackClick: () -> Unit
){

    SharedTransitionLayout(modifier = modifier) {
        AnimatedContent(
            targetState = showDetails,
            label = "transition"
        ) { targetState ->

            if(!targetState){
                CoffeeScreen(
                    coffeeList = coffees,
                    animatedVisibilityScope = this,
                    onCoffeeItemClick = onCoffeeClick
                )
            } else {

                val selectedCoffee = coffees.find { it.id == selectedCoffeeId }

                selectedCoffee?.let { coffee ->

                    CoffeeDetailScreen(
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(MaterialTheme.shapes.small.copy(all = CornerSize(25.dp)))
                            .background(Color.LightGray.copy(alpha = 0.5f))
                            // Adding shared element for the detailed view of the coffee item
                            // The sharedElement modifier is used to specify the shared element for the transition.
                            // - `rememberSharedContentState(key = coffee.id)` creates a state holder for the shared element with a unique key.
                            // - `animatedVisibilityScope` provides the scope for managing visibility changes during the transition.
                            // - `boundsTransform` defines the transformation applied to the bounds of the shared element during the transition.
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(key = coffee.id),
                                animatedVisibilityScope = this,
                                boundsTransform = boundsTransform
                            ),
                        coffee = coffee,
                        onBack = onBackClick
                    )
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
private fun TransitionWithoutNavigationScreenPreview(){

    SharedTransitionComposeTheme {
        TransitionWithoutNavigationScreen(onBack = {/*Click Action*/})
    }
}



@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MainContentPreview(){

    val coffees = FakeDataProvider.getCoffees()

    SharedTransitionComposeTheme {
        MainContent(
            coffees = coffees,
            selectedCoffeeId = 0,
            showDetails = false,
            onCoffeeClick = {},
            onBackClick = {}
        )
    }
}


@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MainContentWithDetailsPreview(){

    val coffees = FakeDataProvider.getCoffees()

    SharedTransitionComposeTheme {
        MainContent(
            coffees = coffees,
            selectedCoffeeId = 0,
            showDetails = true,
            onCoffeeClick = {},
            onBackClick = {}
        )
    }
}


