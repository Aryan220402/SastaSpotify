package com.example.sastaspotify


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sastaspotify.ui.theme.SastaSpotifyTheme

@Composable
fun Bottomplayer(){
    Card(border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier
            .padding(10.dp)
            .height(58.dp)
            .fillMaxWidth()
            .size(70.dp)){
        Row() {
            Image(painter = painterResource(id =  R.drawable.dhvani_bhanushani), contentDescription =null , Modifier.size(50.dp))
            Text(text = "Song name", modifier = Modifier.padding(horizontal = 25.dp, vertical = 9.dp), style = MaterialTheme.typography.h6)
            Image(painter = painterResource(id =  R.drawable.fav), contentDescription =null, modifier= Modifier
                .size(50.dp)
                .padding(horizontal = 10.dp))
            Image(painter = painterResource(id = R.drawable.play), contentDescription = null, modifier= Modifier
                .size(50.dp)
                .padding(horizontal = 10.dp))
            Image(painter = painterResource(id =  R.drawable.next), contentDescription =null, modifier= Modifier
                .size(50.dp)
                .padding(horizontal = 8.dp) )
        }
    }
}
@Preview
@Composable
fun per(){
    SastaSpotifyTheme {
        Bottomplayer()
    }
}