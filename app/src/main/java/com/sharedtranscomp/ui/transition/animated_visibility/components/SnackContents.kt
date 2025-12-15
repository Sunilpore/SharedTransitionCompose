package com.sharedtranscomp.ui.transition.animated_visibility.components


import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharedtranscomp.R
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme


@Composable
fun SnackContents(
    modifier: Modifier = Modifier,
    name:String,
    image:Int
){

    Column(
        modifier = modifier
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(20f / 9f),
            painter = painterResource(id = image),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(8.dp),
            text = name,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium
        )
    }
}


//------------------------------------------------------------------//
//Preview//

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun SnackContentsPreview(){

    SharedTransitionComposeTheme {
        SnackContents(
            name = "Cupcake",
            image =  R.drawable.cupcake
        )
    }
}



