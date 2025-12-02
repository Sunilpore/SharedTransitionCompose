package com.sharedtranscomp.data
import com.sharedtranscomp.R
import com.sharedtranscomp.model.Album


object FakeDataProvider {

    fun getAlbums(): List<Album> =
        listOf(
            Album(
                id = 0,
                title = "It happened Quiet",
                author = "Aurora",
                year = 2018,
                cover = R.drawable.img_album_01
            ),
            Album(
                id = 1,
                title = "All My Daemons",
                author = "Aurora",
                year = 2016,
                cover = R.drawable.img_album_02
            ),
            Album(
                id = 2,
                title = "Running",
                author = "Aurora",
                year = 2015,
                cover = R.drawable.img_album_03
            ),
            Album(
                id = 3,
                title = "Paradise",
                author = "Aurora",
                year = 2015,
                cover = R.drawable.img_album_04
            ),
            Album(
                id = 4,
                title = "Heroz Falling",
                author = "Aurora",
                year = 2015,
                cover = R.drawable.img_album_05
            ),
            Album(
                id = 5,
                title = "Better World",
                author = "Aurora",
                year = 2015,
                cover = R.drawable.img_album_06
            ),
            Album(
                id = 6,
                title = "Master Memories",
                author = "Aurora",
                year = 2015,
                cover = R.drawable.img_album_07
            ),
            Album(
                id = 7,
                title = "Chamber",
                author = "Aurora",
                year = 2015,
                cover = R.drawable.img_album_08
            )
        )

}