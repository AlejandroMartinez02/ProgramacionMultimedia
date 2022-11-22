package es.amm.monkeyfilmlab

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import es.amm.monkeyfilmlab.model.Routes
import es.amm.monkeyfilmlab.ui.screens.*

@Composable
fun CustomNavigator(){
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = Routes.ScreenOne.route){
        composable(route = Routes.ScreenOne.route) {
            ScreenOne(navigationController)
        }
        composable(route = Routes.ScreenTwo.route) {
            ScreenTwo(navigationController)
        }
        composable(route = Routes.ScreenThree.route, arguments = listOf(navArgument("score") {
            type = NavType.IntType
        })) { navBackStackEntry->
            ScreenThree(
                NavigationController = navigationController,
            score = navBackStackEntry.arguments?.getInt("score") ?:0)
        }
        composable(route = Routes.ScreenFour.route, arguments = listOf(navArgument("title"){
            defaultValue = "Hola mundo"
        })) {
            ScreenFour(navigationController,
            title = it.arguments?.getString("title"))
        }
    }
}