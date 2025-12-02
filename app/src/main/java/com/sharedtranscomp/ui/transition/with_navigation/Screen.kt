package com.sharedtranscomp.ui.transition.with_navigation

sealed class Screen (val route: String){
    data object AlbumsScreen: Screen("list")
    data object AlbumDetailsScreen: Screen("details/{albumId}"){
        fun createRoute(albumId: Int) = "details/$albumId"
    }
}