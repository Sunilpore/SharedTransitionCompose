package com.sharedtranscomp.ui.transition.component

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharedtranscomp.R
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.InformationPanel(
    modifier: Modifier = Modifier,
    description: String
){

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(R.string.about),
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Text(
            modifier = Modifier.skipToLookaheadSize(),
            text = description,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }

}



@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun InformationPanelPreview(){

    SharedTransitionComposeTheme {
        SharedTransitionLayout {
            InformationPanel(description = stringResource(R.string.album_description))
        }
    }
}