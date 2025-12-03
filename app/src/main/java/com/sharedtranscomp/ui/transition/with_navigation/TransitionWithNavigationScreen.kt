package com.sharedtranscomp.ui.transition.with_navigation

import android.R.attr.type
import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.sharedtranscomp.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sharedtranscomp.data.FakeDataProvider
import com.sharedtranscomp.model.Album
import com.sharedtranscomp.ui.theme.Purple40
import com.sharedtranscomp.ui.theme.Purple80
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme


/**
 * Composable function for the shared element transition demo with navigation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransitionWithNavigationScreen (
    modifier: Modifier = Modifier,
    onBack: ()-> Unit
){

    val navController = rememberNavController()
    val albums = remember(Unit) { FakeDataProvider.getAlbums() }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.shared_element_transition_with_navigation))
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(Purple40) ,
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        content = {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        MainContent(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            albums = albums
        )
    }
}



/**
 * Composable function that sets up the navigation and shared element transitions
 * for the album preview and detail screens.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavHostController,
    albums: List<Album>
){

    SharedTransitionLayout(
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.AlbumsScreen.route,
        ){

            composable(route = Screen.AlbumsScreen.route){
                AlbumsScreen(
                    albums = albums,
                    animatedVisibilityScope = this,
                    onAlbumClick = { album ->
                        navController.navigate(Screen.AlbumDetailsScreen.createRoute(album.id)){
                            popUpTo(Screen.AlbumsScreen.route){
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = Screen.AlbumDetailsScreen.route,
                arguments = listOf(navArgument("albumId"){ type = NavType.IntType })
            ){ navBackStackEntry ->
                val albumId = navBackStackEntry.arguments?.getInt("albumId") ?: -1
                val album = albums[albumId]

                AlbumDetailScreen(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(MaterialTheme.shapes.small.copy(all = CornerSize(25.dp)))
                        .background(Color.LightGray.copy(alpha = 0.5f))
                        // Adding shared element for the detailed view of the album cover
                        // The sharedElement modifier is used to specify the shared element for the transition.
                        // - `rememberSharedContentState(key = album.id)` creates a state holder for the shared element with a unique key.
                        // - `animatedVisibilityScope` provides the scope for managing visibility changes during the transition.
                        // - `boundsTransform` defines the transformation applied to the bounds of the shared element during the transition.
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = album.id),
                            animatedVisibilityScope = this,
                            boundsTransform = albumBoundsTransform
                        ),
                    album = album,
                    onBackClick = {
                        navController.navigate(Screen.AlbumsScreen.route){
                            popUpTo(Screen.AlbumDetailsScreen.createRoute(albumId = albumId)){
                                inclusive = true
                            }
                        }
                    }
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
fun TransitionWithNavigationScreenPreview(){

    SharedTransitionComposeTheme {
        TransitionWithNavigationScreen(
            onBack = {/*Click Action*/}
        )
    }
}