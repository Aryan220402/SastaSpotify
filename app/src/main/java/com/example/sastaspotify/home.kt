package com.example.sastaspotify

//import com.example.sastaspotify.data.model.SingerPlaylist
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sastaspotify.data.DataProvider
import com.example.sastaspotify.data.MoodAlbums
import com.example.sastaspotify.data.SingerList
import com.example.sastaspotify.data.popularAlbums
//import com.google.gson.Gson
import com.google.gson.Gson

@Composable
fun home(navController:NavController){
        HomeScreen(navController)
}
@Composable
fun HomeScreen(navController: NavController){
    Column(modifier=Modifier
        .verticalScroll(rememberScrollState())
        .padding(vertical = 16.dp)) {
        Column {
            SearchBar()
            AlignYourBodyRow(navController = navController)
            AlignYourBodyRowPlaylist(navController=navController)
            AlignYourBodyRowPlaylistMood(navController=navController)
        }
    }
}
@Composable
fun SearchBar(modifier: Modifier = Modifier)
{
    Column {
        TextField(
            value = "",
            onValueChange = {},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            placeholder = {
                Text(text = "Search")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .heightIn(min = 52.dp),
            shape = RoundedCornerShape(8.dp),
        )
        Text("Popular Artists", style = MaterialTheme.typography.h4, color = Color.Black, modifier = modifier.padding(8.dp) )
    }
}
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier, navController: NavController

) {
    fun navigateToSinger(singerPlaylist: DataProvider){
        val singerJason=Gson().toJson(singerPlaylist)
        navController.navigate("singerDetail/$singerJason")
    }

    Column {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
        ) {
            items(items = SingerList) { item ->
                AlignYourBodyElement(
                    drawable = item.singerImage,
                    text = item.singerName,
                    Modifier.clickable (onClick = {navigateToSinger(item) })
                )
            }
        }
        Text("Popular Albums", style = MaterialTheme.typography.h4, color = Color.Black, modifier = modifier.padding(8.dp))
    }
}
@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
     text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(drawable),
            contentDescription =null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(95.dp)
                .clip(CircleShape)
        )
        Text(
            text= text,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.paddingFromBaseline(
                top=24.dp, bottom = 8.dp
            )
        )
    }
}
@Composable
fun AlignYourBodyRowPlaylist(
    modifier: Modifier = Modifier, navController: NavController
) {
    fun navigateToSingerPlayList(singerPlaylistView: DataProvider){
        val singerJasonPlaylistMood=Gson().toJson(singerPlaylistView)
        navController.navigate("playListDetail/$singerJasonPlaylistMood")
    }
    Column {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
        ) {
            items(popularAlbums) { item ->
                AlignYourBodyElementPlaylist(
                    drawable = item.singerImage,
                    text = item.singerName,
                    Modifier.clickable (onClick = { navigateToSingerPlayList(item)})
                )
            }
        }
        Text("Mood", style = MaterialTheme.typography.h4, color = Color.Black, modifier = modifier.padding(8.dp))
    }
}
@Composable
fun AlignYourBodyElementPlaylist(
    @DrawableRes drawable: Int,
      text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(drawable),
            contentDescription =null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(7.dp))
        )
        Text(
            text= text,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.paddingFromBaseline(
                top=24.dp, bottom = 8.dp
            )
        )
    }
}
@Composable
fun AlignYourBodyRowPlaylistMood(
    modifier: Modifier = Modifier,navController: NavController
) {
    fun navigateToSingerMood(singerPlaylistMood: DataProvider){
        val singerJasonmood=Gson().toJson(singerPlaylistMood)
        navController.navigate("singerPlaylistsMood/$singerJasonmood")
    }
    Column {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
        ) {
            items(MoodAlbums) { items ->
                AlignYourBodyElementPlaylistMood(
                    drawable = items.singerImage,
                    text = items.singerName,
                    Modifier.clickable (onClick = { navigateToSingerMood(items)})
                )
            }
        }
        Text("Popular Albums", style = MaterialTheme.typography.h4, color = Color.Black, modifier = modifier.padding(8.dp))
    }
}
@Composable
fun AlignYourBodyElementPlaylistMood(
    @DrawableRes drawable: Int,
    text: String ,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(drawable),
            contentDescription =null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(7.dp))
        )
        Text(
            text= text,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.paddingFromBaseline(
                top=24.dp, bottom = 8.dp
            )
        )
    }
}

