package com.example.syscredit.ui.composables

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.syscredit.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    titleScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    CenterAlignedTopAppBar(
        title = { Text(titleScreen) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.colorPrimary),
            titleContentColor = colorResource(id = R.color.colorWhite),
        ),
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = navigateUp
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right_white),
                    contentDescription = "",
                    tint = colorResource(id = R.color.colorWhite)
                )
            }
        }
    )
}

