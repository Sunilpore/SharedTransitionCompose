package com.sharedtranscomp.ui.transition.without_navigation

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharedtranscomp.data.FakeDataProvider
import com.sharedtranscomp.model.Coffee
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme


/**
 * Transformation for the shared element bounds.
 * Defines the tween animation for the shared element transitions.
 */
private val boundsTransform = { _: Rect, _: Rect ->
    tween<Rect>(500)
}



/**
 * Composable function that displays the list of coffee items.
 */
@Composable
fun SharedTransitionScope.CoffeeScreen(
    modifier: Modifier = Modifier,
    coffeeList: List<Coffee>,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onCoffeeItemClick: (Coffee) -> Unit
){

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(coffeeList){ coffee ->
            CoffeeItem(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small.copy(all = CornerSize(20.dp)))
                    .background(Color.LightGray)
                    .padding(10.dp),
                animatedVisibilityScope = animatedVisibilityScope,
                coffee = coffee,
                onClick = {
                    onCoffeeItemClick(coffee)
                }
            )
        }

    }

}


/**
 * Composable function that displays a single coffee item with a shared element.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CoffeeItem(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    coffee: Coffee,
    onClick: () -> Unit
){

    Row(
        modifier = modifier.clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = coffee.id),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = boundsTransform
                )
                .size(100.dp)
                .clip(CircleShape),
            painter = painterResource(id = coffee.image),
            contentDescription = coffee.name,
            contentScale = ContentScale.Crop
        )

        Column {
            Text(
                text = coffee.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium
            )
        }

    }

}


//------------------------------------------------------------------//
//Preview//


@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CoffeeScreenPreview(){

  var coffeeList =  FakeDataProvider.getCoffees()

    SharedTransitionComposeTheme {
        SharedTransitionLayout {
            AnimatedVisibility(true) {
                CoffeeScreen(
                    modifier = Modifier.background(Color.White),
                    coffeeList = coffeeList,
                    animatedVisibilityScope = this
                )  {
                    /* Click Action */
                }
            }
        }
    }

}


@Composable
@Preview(showBackground = true)
fun CoffeeItemPreview(){

    var coffee = FakeDataProvider.getCoffees()[0]

    SharedTransitionComposeTheme {
        SharedTransitionLayout {
            AnimatedVisibility(true) {
                CoffeeItem(
                    modifier = Modifier.fillMaxWidth(),
                    coffee = coffee,
                    animatedVisibilityScope = this
                ){
                    /* Click Action */
                }
            }

        }

    }
}