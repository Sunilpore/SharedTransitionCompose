package com.sharedtranscomp.ui.transition.sheet

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


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

        ) { }

    }

}