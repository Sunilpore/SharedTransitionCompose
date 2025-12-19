package com.sharedtranscomp.ui.transition.sheet.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sharedtranscomp.R
import com.sharedtranscomp.ui.theme.SharedTransitionComposeTheme


@Composable
fun AlbumPlayControls(
    modifier: Modifier = Modifier,
    playControlSize: Dp
){

    Row(
        modifier = modifier
    ) {

        Icon(
            modifier = Modifier.size(playControlSize),
            painter = painterResource(R.drawable.baseline_skip_previous_24),
            contentDescription = stringResource(R.string.app_name),
            tint = MaterialTheme.colorScheme.onSurface
        )
        Icon(
            modifier = Modifier.size(playControlSize),
            imageVector = ImageVector.vectorResource(R.drawable.ic_auto_play),
            contentDescription = stringResource(R.string.app_name),
            tint = MaterialTheme.colorScheme.onSurface
        )
        Icon(
            modifier = Modifier.size(playControlSize),
            imageVector = ImageVector.vectorResource(R.drawable.baseline_skip_next_24),
            contentDescription = stringResource(R.string.app_name),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}


//------------------------------------------------------------------//
//Preview//

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AlbumPlayControlsPreview(){

    SharedTransitionComposeTheme {
        AlbumPlayControls(playControlSize = 20.dp)
    }
}



