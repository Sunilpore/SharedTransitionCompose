package com.sharedtranscomp.ui.transition.with_navigation

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharedtranscomp.R
import com.sharedtranscomp.data.FakeDataProvider
import com.sharedtranscomp.model.Album
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme
import com.sharedtranscomp.ui.transition.component.InformationPanel



@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AlbumDetailScreen(
    modifier: Modifier = Modifier,
    album: Album,
    onBackClick: () -> Unit
){
    Column(
        modifier = modifier
    ) {
        AlbumDetailHeader(
            cover = painterResource(id = album.cover),
            onBackClick = onBackClick)

        AlbumDetailInfo(
            modifier = Modifier.padding(20.dp),
            title = album.title,
            subtitle = "${album.author} ${album.year}"
        )

        AlbumDetailDescription(
            modifier = Modifier.padding(10.dp)
        )
    }

}


/**
 * Composable function for displaying the album detail header with the album cover image.
 */
@Composable
private fun AlbumDetailHeader(
    modifier: Modifier = Modifier,
    cover: Painter,
    onBackClick: () -> Unit
){
    Box(
        modifier = modifier
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(MaterialTheme.shapes.small.copy(all = CornerSize(25.dp))),
            painter = cover,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable(onClick = onBackClick)
        ) {
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp),
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                tint = Color.Black,
                contentDescription = null
            )
        }
    }

}


/**
 * Composable function for displaying the album information such as title, author, and year.
 */
@Composable
private fun AlbumDetailInfo(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
){
    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = subtitle,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Magenta.copy(alpha = 0.3f)),
                onClick = { /* Handle play action */ }
            ) {
                Icon(
                    modifier = Modifier
                        .size(50.dp),
                    imageVector = Icons.Filled.PlayArrow,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            }
        }
    }
}


/**
 * Composable function for displaying the album description.
 * Uses shared element transition scope to ensure smooth transitions.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.AlbumDetailDescription(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        InformationPanel(
            modifier = Modifier.padding(horizontal = 16.dp),
            description = stringResource(R.string.album_description)
        )
    }
}

//------------------------------------------------------------------//


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumDetailScreenPreview(){

    val album = FakeDataProvider.getAlbums()[0]

    SharedTransitionComposeTheme {
        SharedTransitionLayout {
            AlbumDetailScreen(album = album){
                /*Click Action*/
            }
        }

    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumDetailHeaderPreview(){

    SharedTransitionComposeTheme {
        AlbumDetailHeader(
            cover = painterResource(R.drawable.img_album_01)
        ) {
            /*Click Action*/
        }
    }

}


@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumDetailInfoPreview(){

    SharedTransitionComposeTheme {
        AlbumDetailInfo(
            modifier = Modifier.fillMaxWidth(),
            title = "It happned quite",
            subtitle = "Aurora, 2018"
        )
    }
}



@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumDetailDescriptionPreview(){

    SharedTransitionComposeTheme {
        SharedTransitionLayout {
            AlbumDetailDescription()
        }
    }


}




