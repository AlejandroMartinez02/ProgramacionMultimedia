package es.amm.monkeyfilmlab.model

sealed class Routes(val route : String){
    object ScreenOne : Routes("screeOne")
    object ScreenTwo : Routes("screeTwo")
    object ScreenThree : Routes("screenThree/{score}"){
        fun createRoute(score : Int) = "screenThree/$score"
    }
    object ScreenFour : Routes("screenFour/{title}"){
        fun createRoute(title : String) = "screenFour/$title"
    }
}
