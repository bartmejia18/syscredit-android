package com.example.syscredit.ui.unlocks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.syscredit.ui.unlocks.BlockedViewModel
import com.example.syscredit.ui.unlocks.ui.BlockedScreen
import com.example.syscredit.ui.unlocks.ui.DetailScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    viewModel: BlockedViewModel,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoute.Blocked.path
    ) {
        addBlockedScreen(viewModel, navController, this)
        addLockDetailsScreen(viewModel, navController, this)
    }
}

private fun addBlockedScreen(
    viewModel: BlockedViewModel,
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Blocked.path) {
        //BlockedScreen(viewModel)
    }
}

private fun addLockDetailsScreen(
    viewModel: BlockedViewModel,
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.LockDetails.path) {
        DetailScreen()
    }
}


