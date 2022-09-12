package com.example.sastaspotify

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sastaspotify.ViewModel.MainViewModel
import com.example.sastaspotify.data.DataProvider
import com.example.sastaspotify.data.firebaseDataFile
import com.google.gson.Gson

var arrayList=MainViewModel()
@Composable
fun SingerPhotoCard(singerPlaylist: DataProvider, navController: NavController){
    Column( Modifier.padding(vertical = 20.dp)) {
        Row(Modifier.padding(vertical = 6.dp, horizontal = 10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.prev),
                contentDescription = null,
                Modifier
                    .size(50.dp)
                    .padding(vertical = 10.dp)
                    .clickable(onClick = { navController.navigate(Screen.Home.toString()) })
            )
            Text(
                text = singerPlaylist.singerName,
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp),
                style = MaterialTheme.typography.h5
            )
        }
        Card(modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 70.dp)
            .size(200.dp),
            backgroundColor = Color.Red) {
            Image(painterResource(id =singerPlaylist.singerImage), contentDescription = null, modifier = Modifier.size(200.dp))
        }
        Image(painter = painterResource(id = R.drawable.play), contentDescription = null , modifier = Modifier
            .padding(horizontal = 135.dp)
            .size(60.dp))
        PlayListView(names = arrayList.temList, navController)
    }
}
@Composable
fun PlayListView(names:MutableList<firebaseDataFile>, navController: NavController){


    LazyColumn(modifier = Modifier.padding(horizontal = 5.dp, vertical = 4.dp)) {
        items(items = names) { name ->
            Gretting(name, navController)
        }
    }
}
@Composable
fun Gretting(name:firebaseDataFile, navController: NavController){
    Card(backgroundColor = Color.White, modifier = Modifier
        .padding(vertical = 8.dp)
        .height(70.dp)){
        CardContent(name, navController)
    }
}
@Composable
private fun CardContent(name:firebaseDataFile, navController: NavController){
    fun navigateToSingerPlaySong(singerPlaylistView: firebaseDataFile){
        val PlaySong= Gson().toJson(singerPlaylistView)
        navController.navigate("PlayerFull/$PlaySong")
    }
    Row(modifier = Modifier.padding(horizontal = 8.dp).clickable{}){
        Column(modifier= Modifier
            .padding(horizontal = 8.dp)
            .weight(1f)) {
            Row(modifier = Modifier.padding( vertical = 12.dp).clickable(onClick = {navigateToSingerPlaySong(name)} )) {

                Image(painter = rememberAsyncImagePainter(model = name.imageurl), contentDescription =null, modifier = Modifier.size(60.dp).height(30.dp) )
                Text(text = name.name, modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp), style = MaterialTheme.typography.h6)
            }
        }
    }
}