package com.aslan.learncompose.ui

import androidx.annotation.DrawableRes
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aslan.learncompose.R

sealed class NavigationItem(val route: String, val title: String, @DrawableRes val icon: Int) {
    object Home : NavigationItem("home", "Home", R.drawable.ic_home)
    object Notification : NavigationItem("notification", "Notification", R.drawable.ic_notification)
    object Profile : NavigationItem("profile", "Profile", R.drawable.ic_profile)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation(navController: NavHostController, bsState: ModalBottomSheetState) {
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(bsState)
        }

        composable(NavigationItem.Notification.route) {
            NotificationScreen()
        }

        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }


    }
}

@Preview
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Notification,
        NavigationItem.Profile,
    )

    BottomNavigation() {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route = route) {
                                saveState = true
                            }
                        }

                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true

                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                label = { Text(text = item.title) },
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) }
            )
        }
    }
}