package com.example.sastaspotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sastaspotify.ViewModel.MainViewModel
import com.example.sastaspotify.data.DataProvider
import com.example.sastaspotify.data.firebaseDataFile
import com.example.sastaspotify.ui.theme.SastaSpotifyTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SastaSpotifyTheme {
                        sastaSpotity()
                }
                }
            }
}
@Composable
fun sastaSpotity(){
    val navController= rememberNavController()
    SastaSpotifyTheme {
        Scaffold(bottomBar = { BottonNavigation(navController = navController) }
        )
        {
            Navigation(navController=navController)
        }
    }
}

@Composable
private fun BottonNavigation(
     navController: NavController
){ val items= listOf(
    BottomNavItem.Home,
    BottomNavItem.Favorite,
    BottomNavItem.Playlist,
    BottomNavItem.Download,
    BottomNavItem.Setting
)

    BottomNavigation(
        Modifier.border(1.dp, color = Color.Black),
    backgroundColor = MaterialTheme.colors.primaryVariant)
    {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute=navBackStackEntry?.destination?.route
        items.forEach{
            item -> BottomNavigationItem(
            icon ={ Icon(painterResource(id = item.icon), contentDescription = item.title)},
            label={Text(text = item.title, fontSize = 10.sp)},
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.Black.copy(0.4f),

            alwaysShowLabel = true,
            selected = currentRoute==item.screen_route,
            onClick = {
                navController.navigate(item.screen_route){
                    navController.graph.startDestinationRoute?.let { screen_route ->popUpTo(screen_route){
                        saveState=true
                    } }
                    launchSingleTop=true
                    restoreState=true
                }
            }
            )
        }
    }

}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation(navController:NavHostController){
    NavHost(navController = navController,
        startDestination = BottomNavItem.Home.screen_route) {
        composable( "splashScreen"){
            SplashScreen(navController = navController)
        }
        composable(BottomNavItem.Home.screen_route){
            home(navController=navController)
        }

        composable(BottomNavItem.Favorite.screen_route){
            Favorite()
        }

        composable(BottomNavItem.Download.screen_route){
            download()
        }
        composable(BottomNavItem.Playlist.screen_route){
             playlists()
        }
        composable(BottomNavItem.Setting.screen_route){
            Setting(navController=navController)
        }
        composable( Screen.Quality.toString()){
            qualityBottomSheet()
        }
        composable(Screen.Profile.toString()){
            ProfileScreen(navController = navController)
        }
        composable(Screen.Setting.toString()){
            Setting(navController = navController)
        }

        composable(Screen.About.toString()){
            About(navController=navController)
        }
        composable(Screen.Home.toString()){
            home(navController=navController)
        }
        composable("singerDetail/{singer}", arguments = listOf(navArgument("singer"){
            type= NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("singer")?.let { json ->
                val singer = Gson().fromJson(json, DataProvider::class.java)
                SingerPhotoCard(singerPlaylist = singer, navController = navController)
            }
        }
        composable("playListDetail/{singerPlaylists}", arguments = listOf(navArgument("singerPlaylists"){
            type= NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("singerPlaylists")?.let { json ->
                val singer = Gson().fromJson(json, DataProvider::class.java)
                SingerPhotoCard(singerPlaylist = singer, navController = navController)
            }
        }
        composable("PlayerFull/{PlaySong}", arguments = listOf(navArgument("PlaySong"){
            type=NavType.StringType
        })){
         navBackStackEntry ->navBackStackEntry.arguments?.getString("PlaySong")?.let{
             json ->val song =Gson().fromJson(json, firebaseDataFile::class.java)
            Player( songDetail=song, navController )
        }
        }
    }
}
