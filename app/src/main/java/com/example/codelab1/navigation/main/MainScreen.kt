package com.example.codelab1.navigation.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlin.math.roundToInt


@Composable
fun MainScreen(navHostController: NavHostController= rememberNavController()){

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route){

    }

    val bottomBarHeight = 56.dp
    val bottomBarHeightPx = with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }

//    val nestedScrollConnection = remember {
//        object : NestedScrollConnection {
//            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//                val delta = available.y
//                val newOffset = bottomBarOffsetHeightPx.value + delta
//                bottomBarOffsetHeightPx.value = newOffset.coerceIn(-bottomBarHeightPx, 0f)
//
//                return Offset.Zero
//            }
//        }
//    }

    Scaffold(modifier = Modifier,
    bottomBar = { BottomBar(navHostController = navHostController, state = bottomBarState,
        modifier = Modifier
            .height(bottomBarHeight)
            .offset { IntOffset(x = 0, y = -bottomBarOffsetHeightPx.value.roundToInt()) }
        )
}){

        MainGraph(navHostController)
    }
}
@Composable
fun BottomBar(
    navHostController: NavHostController,
    state: MutableState<Boolean>,
    modifier: Modifier
) {

    val screens = listOf(Screens.CoinsScreen,Screens.CoinsNews,Screens.CoinsWatchList)
    AnimatedVisibility(visible = state.value,
    enter = slideInVertically (initialOffsetY = {it}),
    exit = slideOutVertically(targetOffsetY = { it})
        ) {
        BottomNavigation(modifier=modifier, backgroundColor = Color.Black) {
            val backStackEntry by navHostController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route

            screens.forEach { screen->
                BottomNavigationItem(selected = currentRoute == screen.route ,
                    onClick = {
                            navHostController.navigate(screen.route){
                                popUpTo(navHostController.graph.findStartDestination().id){
                                    saveState = true
                                }

                                launchSingleTop = true
                                restoreState = true
                            }

                              },
                    label = {Text(text = screen.title!!)},
                    icon = {Icon(imageVector = screen.icon!!, contentDescription = "")},
                alwaysShowLabel = false,
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Gray
                    )
            }
        }

    }

}